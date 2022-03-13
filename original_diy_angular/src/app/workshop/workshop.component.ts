import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {TokenStorageService} from "../service/token-storage.service";
import {WorkshopService} from "../service/workshop.service";
import {DiyWorkshop} from "../model/workshop.model";
import {ActivatedRoute} from "@angular/router";

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

  constructor(private title: Title, private tokenStorageService: TokenStorageService,
              private workshopService: WorkshopService, private route: ActivatedRoute) {
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
    }
  }

  getWorkshop(id: string): void {
    this.workshopService.getById(id).subscribe({
      next: (data) => {
        this.workshop = data;

        console.log(data);
      },

      error: (err) => console.error(err)
    });
  }

}
