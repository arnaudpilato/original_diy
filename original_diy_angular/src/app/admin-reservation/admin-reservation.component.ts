import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../service/token-storage.service";
import {WorkshopService} from "../service/workshop.service";

@Component({
  selector: 'app-admin-reservation',
  templateUrl: './admin-reservation.component.html',
  styleUrls: ['./admin-reservation.component.scss']
})
export class AdminReservationComponent implements OnInit {
  public workshops: any[] | undefined;
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  public isLoggedIn: boolean | undefined;
  private roles: any;
  public showAdminBoard: any;
  private currentUser: any;

  constructor(private tokenStorageService: TokenStorageService, private workshopService: WorkshopService) {
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.currentUser = this.tokenStorageService.getUser();
    }
    console.log(this.isLoggedIn + " : login "+this.showAdminBoard+":showadmin")
    this.getWorkshop();
  }

  getWorkshop(): void {
    this.workshopService.getAll().subscribe({
      next: (datas) => {
        this.workshops = datas;
        console.log("thisWorkshops = ", this.workshops)
      },

      error: (e) => console.log(e)
    })
  }

  deleteReservation(username: any, workshopId: any): void {
    this.workshopService.deleteReservationByUsername(username, workshopId).subscribe({
      next: (res) => {
        window.location.reload();
      },

      error: (e) => console.error(e)
    });
  }
}
