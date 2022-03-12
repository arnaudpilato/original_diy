import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../service/token-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin-badge',
  templateUrl: './admin-badge.component.html',
  styleUrls: ['./admin-badge.component.scss']
})
export class AdminBadgeComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;

  constructor(private tokenStorageService: TokenStorageService, private router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
    }
  }
}
