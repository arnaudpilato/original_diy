import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {TokenStorageService} from "../service/token-storage.service";
import {WorkshopService} from "../service/workshop.service";
import {DiyWorkshop} from "../model/workshop.model";
import {ActivatedRoute} from "@angular/router";
import {DiyUser} from "../model/user.model";

@Component({
  selector: 'app-workshop',
  templateUrl: './workshop.component.html',
  styleUrls: ['./workshop.component.scss']
})
export class WorkshopComponent implements OnInit {
  public workshop: any = new DiyWorkshop();
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  public static: string = '/assets/img/static-picture.png';
  public model: DiyWorkshop = new DiyWorkshop();
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public currentUser: any;
  private roles: string[] = [];
  public userReservation: any = [];

  constructor(
      private title: Title,
      private tokenStorageService: TokenStorageService,
      private workshopService: WorkshopService,
      private route: ActivatedRoute) {
    this.title.setTitle('Atelier');
  }

  ngOnInit(): void {
    this.getWorkshop(this.route.snapshot.params["id"]);
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.currentUser = this.tokenStorageService.getUser();
    }
  }

  getWorkshop(id: number): void {
    this.workshopService.getById(id).subscribe({
      next: (data) => {
        this.workshop = data;
        this.userReservation = this.workshop.reservationUser.map((el: { username: any; }) => el.username);
      },

      error: (err) => console.error(err)
    });
  }

  reservation(id: number): void {
    this.workshopService.reservation(id, this.model).subscribe({
      next: (data) => {
        this.model = data;
        window.location.reload();
      },

      error: (err) => console.error(err)
    });
  }

  deleteReservationUser(id: number): void {
    this.workshopService.deleteReservation(id).subscribe({
      next: (res) => {
        window.location.reload();
      },

      error: (e) => console.error(e)
    });
  }
}
