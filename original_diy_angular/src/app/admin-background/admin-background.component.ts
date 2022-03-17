import { Component, OnInit } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {AmazonS3Service} from "../service/amazon-s3.service";
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "../service/token-storage.service";
import {DiyBackground} from "../model/background.model";
import {BackgroundService} from "../service/background.service";

@Component({
  selector: 'app-admin-background',
  templateUrl: './admin-background.component.html',
  styleUrls: ['./admin-background.component.scss']
})
export class AdminBackgroundComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public backgrounds: DiyBackground[] | undefined;
  public visibility: any;
  public file: any;

  constructor(
      private title:Title,
      private backgroundService: BackgroundService,
      private amazonS3Service: AmazonS3Service,
      private http: HttpClient,
      private tokenStorageService:TokenStorageService) {
    this.title.setTitle("OriginalDIY - Admin - background")
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.getAllBackgrounds();
      console.log(this.backgrounds);
    }
  }

  getAllBackgrounds(): void {
    this.backgroundService.getAll().subscribe({
      next: (data) => {
        this.backgrounds = data;
        this.visibility = this.backgrounds.length;
        console.log(data);
      },

      error: (err) => console.error(err)
    });
  }

  deleteBackground(id: any, path: any): void {
    if (path != "/assets/img/static-picture.png") {
      this.amazonS3Service.deleteFile(path);
    }

    this.backgroundService.delete(id).subscribe({
      next: (res) => {
        console.log(res);
        window.location.reload();
      },

      error: (e) => console.error(e)
    });
  }
}
