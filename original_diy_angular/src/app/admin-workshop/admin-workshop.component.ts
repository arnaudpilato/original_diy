import { Component, OnInit } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {WorkshopService} from "../service/workshop.service";
import {AmazonS3Service} from "../service/amazon-s3.service";
import {TokenStorageService} from "../service/token-storage.service";

@Component({
  selector: 'app-admin-workshop',
  templateUrl: './admin-workshop.component.html',
  styleUrls: ['./admin-workshop.component.scss']
})
export class AdminWorkshopComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  public static: string = '/assets/img/static-picture.png';
  public workshops:any[] | undefined ;

  constructor(
    private tokenStorageService: TokenStorageService,
    private title: Title,
    private workshopService: WorkshopService,
    private amazonS3Service: AmazonS3Service) {
    this.title.setTitle('Gestion des ateliers');
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.getAllWorkshops();
    }
  }

  getAllWorkshops(): void {
    this.workshopService.getAll().subscribe({
      next: (data) => {
        this.workshops = data;
      },

      error: (err) => console.log(err)
    });
  }

  deleteWorkshop(id: any, path: any): void {
    if (path != "/assets/img/static-picture.png") {
      this.amazonS3Service.deleteFile(path);
    }

    this.workshopService.delete(id).subscribe({
      next: (res) => {
        window.location.reload();
      },

      error: (e) => console.error(e)
    });
  }
}
