import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {TokenStorageService} from "../service/token-storage.service";
import {WorkshopService} from "../service/workshop.service";
import {DiyWorkshop} from "../model/workshop.model";
import {ActivatedRoute} from "@angular/router";
import {BackgroundService} from "../service/background.service";
import {DiyBackground} from "../model/background.model";

@Component({
  selector: 'app-workshop',
  templateUrl: './workshop.component.html',
  styleUrls: ['./workshop.component.scss']
})
export class WorkshopComponent implements OnInit {
  private roles: string[] = [];
  public isSignUpFailed: boolean = false;
  public isLoggedIn: boolean = false;
  public currentUser: any;
  public currentToken: any;
  public workshop: any = new DiyWorkshop();
  public showAdminBoard: boolean = false;
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  public static: string = '/assets/img/static-picture.png';
  public background: any = new  DiyBackground();


  constructor(
      private title: Title,
      private tokenStorageService: TokenStorageService,
      private workshopService: WorkshopService,
      private route: ActivatedRoute,
      private backgroundService: BackgroundService) {
        this.title.setTitle("OriginalDIY - Atelier");
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    const user = this.tokenStorageService.getUser();

    if (this.isLoggedIn) {
      this.getWorkshop(this.route.snapshot.params["id"]);
      this.currentUser = this.tokenStorageService.getUser();
      this.currentToken = this.tokenStorageService.getToken();
      this.roles = user.roles;
      console.log(this.roles.includes('ROLE_ADMIN'));
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.getAllBackground();
    }
  }

  getWorkshop(id: number): void {
    this.workshopService.getByIdHome(id).subscribe({
      next: (data) => {
        this.workshop = data;

        console.log(data);
      },

      error: (err) => console.error(err)
    });
  }

  getAllBackground() {
    this.backgroundService.getAll().subscribe({
      next: (data) => {
        this.background = data[1].picturePath
        console.log("nom de l'image " + data[1].picturePath);

      },

      error: (err) => console.error(err)
    })
  }

}
