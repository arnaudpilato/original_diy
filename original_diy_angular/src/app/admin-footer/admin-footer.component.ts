import { Component, OnInit } from '@angular/core';
import { FooterService } from "../service/footer.service";
import { DiyFooter } from "../model/footer.model";
import { Title } from "@angular/platform-browser";
import {AmazonS3Service} from "../service/amazon-s3.service";
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "../service/token-storage.service";

@Component({
  selector: 'app-admin-footer',
  templateUrl: './admin-footer.component.html',
  styleUrls: ['./admin-footer.component.scss']
})
export class AdminFooterComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public socialNetworks: DiyFooter[] | undefined;
  public static: string = '/assets/img/static-picture.png';
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  public file: any;

  constructor(
    private title:Title,
    private footerService: FooterService,
    private amazonS3Service: AmazonS3Service,
    private http: HttpClient,
    private tokenStorageService:TokenStorageService) {
      this.title.setTitle("OriginalDIY - Admin - footer")
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.getAllSocialNetworks();
    }
  }

  getAllSocialNetworks(): void {
    this.footerService.getAll().subscribe({
      next: (data) => {
        this.socialNetworks = data;
      },

      error: (err) => console.error(err)
    });
  }

  deleteSocialNetwork(id: any, path: any): void {
    if (path != "/assets/img/static-picture.png") {
      this.amazonS3Service.deleteFile(path);
    }

    this.footerService.delete(id).subscribe({
      next: (res) => {
        window.location.reload();
      },

      error: (e) => console.error(e)
    });
  }
}
