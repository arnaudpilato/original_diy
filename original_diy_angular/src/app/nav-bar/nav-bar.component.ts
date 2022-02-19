import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../service/token-storage.service";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {
  private role: string = 'ROLE_USER';
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public currentUser: any;

  constructor(private tokenStorageService: TokenStorageService) {
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      this.currentUser = this.tokenStorageService.getUser();
      this.role = this.tokenStorageService.getUser().roles[0].name;

      if (this.role == 'ROLE_ADMIN') {
        this.showAdminBoard = true;
      }
      console.log(this.role);
    }
  }

  public logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
