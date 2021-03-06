import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {TokenStorageService} from "../service/token-storage.service";
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
  public workshop: any | undefined;
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  public static: string = '/assets/img/static-picture.png';

  constructor(
    private title: Title,
    private tokenStorageService: TokenStorageService,
    private workshopService: WorkshopService) {
    this.title.setTitle('Accueil');
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    this.getAllWorkshopsConfirmed();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
    }
  }

  getAllWorkshopsConfirmed(): any {
    this.workshopService.getLastWorkshop().subscribe({
        next: (datas) => {
          this.workshops = datas;
        },

        error: (e) => console.log(e)
      }
    )
  }

  showWorkshop(id: any): void {
    this.workshopService.getByIdHome(id).subscribe({
      next: (datas) => {
        this.workshop = datas;
      },

      error: (err) => console.error(err)
    });
  }
}

