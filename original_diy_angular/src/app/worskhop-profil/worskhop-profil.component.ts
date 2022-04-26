import { Component, OnInit } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {WorkshopService} from "../service/workshop.service";
import {TokenStorageService} from "../service/token-storage.service";
import {Router} from "@angular/router";

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
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  public static: string = '/assets/img/static-picture.png';

  constructor(private title: Title, private workshopService: WorkshopService,
              private token: TokenStorageService,
              private router: Router) {
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


    }
  }

  workshopDetails($event: number) {
    this.router.navigate(['/workshop/' + $event]);
  }

  userWorkshop():any {
    this.workshopService.getWorkshopByUserId().subscribe({
      next: (datas) => {
        this.workshops = datas;
      },

      error: (err) => console.error(err)
    });
  }
}
