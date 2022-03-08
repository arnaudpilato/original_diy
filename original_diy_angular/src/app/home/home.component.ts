import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {TokenStorageService} from "../service/token-storage.service";
import * as L from "leaflet";
import {WorkshopService} from "../service/workshop.service";

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
  public workshops: any[] | undefined;

  constructor(private title: Title, private tokenStorageService: TokenStorageService,
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
      console.log('home role ' + this.roles[0]);
    }
  }

  getAllWorkshopsConfirmed(): any {
    this.workshopService.getLastWorkshop().subscribe({
        next: (datas) => {
           this.workshops = datas;
          console.log("this.workshop = ", this.workshops)

        }
      }
    )
  }


}

