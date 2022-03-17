import { Component, OnInit } from '@angular/core';
import { Title } from "@angular/platform-browser";
import { TokenStorageService } from "../service/token-storage.service";
import { DiyUser } from "../model/user.model";
import { UserService } from "../service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin-contact',
  templateUrl: './admin-contact.component.html',
  styleUrls: ['./admin-contact.component.scss']
})
export class AdminContactComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public users: DiyUser[] | undefined;

  constructor(private title: Title, private tokenStorageService:TokenStorageService, private userService:UserService, private router: Router) {
    this.title.setTitle('OriginalDIY - Admin - Contacts');
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.getAllUsers();
    }
  }

  getAllUsers(): void {
    this.userService.getAll().subscribe({
      next: (data) => {
        this.users = data;
        },

      error: (e) => console.error(e)
    });
  }

  deleteUser(id: any): void {
    this.userService.delete(id).subscribe({
      next: (res) => {
        window.location.reload();
      },

      error: (e) => console.error(e)
    });
  }
}
