import { Component, OnInit } from '@angular/core';
import { Title } from "@angular/platform-browser";
import { TokenStorageService } from "../service/token-storage.service";
import { DiyUser } from "../model/user.model";
import { UserService } from "../service/user.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-admin-contact',
  templateUrl: './admin-contact.component.html',
  styleUrls: ['./admin-contact.component.scss']
})
export class AdminContactComponent implements OnInit {
  public isLoggedIn: boolean = false;
  public currentUser: any;
  public currentToken: any;
  public users: DiyUser[] | undefined;

  constructor(private title: Title, private tokenStorageService:TokenStorageService, private userService:UserService, private router: Router) {
    this.title.setTitle('OriginalDIY - Admin - Contacts');
  }

  ngOnInit(): void {
    this.getAllUsers();
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    console.log(this.users)

    if (this.isLoggedIn) {
      this.currentUser = this.tokenStorageService.getUser();
      this.currentToken = this.tokenStorageService.getToken();
    }
  }

  getAllUsers(): void {
    this.userService.getAll()
      .subscribe({
        next: (data) => {
          this.users = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  deleteUser(id: any): void {
    this.userService.delete(id)
      .subscribe({
        next: (res) => {
          console.log(res);
          window.location.reload();
        },
        error: (e) => console.error(e)
      });
  }
}
