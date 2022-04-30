import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../service/token-storage.service";
import {WorkshopService} from "../service/workshop.service";

@Component({
  selector: 'app-workshop-reservation',
  templateUrl: './workshop-reservation.component.html',
  styleUrls: ['./workshop-reservation.component.scss']
})
export class WorkshopReservationComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public currentUser: any;
  public workshops: any[] | undefined;
  public static: string = '/assets/img/static-picture.png';
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  public workshop: any | undefined;

  constructor(private tokenStorageService: TokenStorageService, private workshopService: WorkshopService) {
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.currentUser = this.tokenStorageService.getUser();
      console.log('Est ce que l\'utilisateur à le role admin ' + this.showAdminBoard);
      console.log('Détails de l\'utilisateur connécté ' + this.currentUser)

    }
    this.getWorkshop();
  }

  getWorkshop(): void {
    this.workshopService.getReservationByCurrentUser().subscribe({
      next: (datas) => {
        this.workshops = datas;
        console.log("thisWorkshops = ", this.workshops)
      },

      error: (e) => console.log(e)
    })
  }

  showWorkshop(id: any): void {
    this.workshopService.getByIdHome(id).subscribe({
      next: (datas) => {
        this.workshop = datas;
      },

      error: (err) => console.error(err)
    });
  }

  deleteReservation(id: any): void {

    this.workshopService.deleteReservation(id).subscribe({
      next: (res) => {
        window.location.reload();
      },

      error: (e) => console.error(e)
    });
  }

  refresh():void {
    window.location.reload();
  }
}
