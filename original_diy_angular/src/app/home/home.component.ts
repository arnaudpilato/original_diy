import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {TokenStorageService} from "../service/token-storage.service";
import * as L from "leaflet";
import {WorkshopService} from "../service/workshop.service";
import {DiyWorkshop} from "../model/workshop.model";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public content: string | undefined;
  public workshops: DiyWorkshop[] | undefined;
  public workshop: DiyWorkshop | undefined;
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  public static: string = '/assets/img/static-picture.png';

  constructor(
    private title: Title,
    private tokenStorageService: TokenStorageService,
    private workshopService: WorkshopService) {
      this.title.setTitle("OriginalDIY - Accueil");
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    this.getAllWorkshopsConfirmed();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      // console.log('home role ' + this.roles[0]);
      //  console.log("this user" , user);
    }

  }

  getAllWorkshopsConfirmed(): any {
    this.workshopService.getLastWorkshop().subscribe({
        next: (datas) => {
          this.workshops = datas;
         // console.log("this.workshops = ", this.workshops)
        },

        error: (e) => console.log(e)
      }
    )
  }

  showWorkshop(id: any):void {
    console.log("ma bite est grosse");
    this.workshopService.getById(id).subscribe({
      next: (datas) => {
        this.workshop = datas;
        console.log("this.workshop = ", this.workshop)
      },

      error: (err) => console.error(err)
    });
  }

}

