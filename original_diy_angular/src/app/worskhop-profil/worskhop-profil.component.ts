import { Component, OnInit } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {WorkshopService} from "../service/workshop.service";
import {TokenStorageService} from "../service/token-storage.service";

@Component({
  selector: 'app-worskhop-profil',
  templateUrl: './worskhop-profil.component.html',
  styleUrls: ['./worskhop-profil.component.scss']
})
export class WorskhopProfilComponent implements OnInit {

  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public isSignUpFailed: boolean = false;
  public errorMessage: string = '';
  public authuser: any;
  public workshops: any[] | undefined;

  constructor(private title: Title, private workshopService: WorkshopService,
              private token: TokenStorageService) {
    this.title.setTitle("OriginalDIY - Mes - Ateliers")
  }

  ngOnInit(): void {
    this.userWorkshop();
    this.isLoggedIn = !!this.token.getToken();
    if (this.isLoggedIn) {
      const user = this.token.getUser();
      this.authuser = this.token.getUser().user;
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      console.log("console log de this.authuser: ", this.authuser);


    }
  }

  userWorkshop():any {
    this.workshopService.getWorkshopByUserId().subscribe({
      next: (datas) => {
        this.workshops = datas;
       // console.log("this.workshop = ", this.workshops)
      },

      error: (err) => console.error(err)
    });
  }
}
