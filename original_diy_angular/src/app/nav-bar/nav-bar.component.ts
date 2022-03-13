import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../service/token-storage.service";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public currentUser: any;

  constructor(private tokenStorageService: TokenStorageService) {
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
  }

  public logout(): void {
    this.tokenStorageService.signOut();
    window.location.href="/home"
  }
}
