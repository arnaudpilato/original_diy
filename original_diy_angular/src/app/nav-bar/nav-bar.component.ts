import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from "../service/token-storage.service";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {
  private roles: Object[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public username: string | undefined;
  public currentUser: any;

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.username = user.username;
      this.currentUser = this.tokenStorageService.getUser();
    }
  }

  public logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
