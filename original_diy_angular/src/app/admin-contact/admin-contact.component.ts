import { Component, OnInit } from '@angular/core';
import { Title } from "@angular/platform-browser";
import { TokenStorageService } from "../service/token-storage.service";
import { DiyUser } from "../model/user.model";
import { UserService } from "../service/user.service";

@Component({
  selector: 'app-admin-contact',
  templateUrl: './admin-contact.component.html',
  styleUrls: ['./admin-contact.component.scss']
})
export class AdminContactComponent implements OnInit {
  public isLoggedIn: boolean = false;
  public currentUser: any;
  public currentToken: any;
  public users?: DiyUser[];

  constructor(private title: Title, private tokenStorageService:TokenStorageService, private userService:UserService) {
    this.title.setTitle('OriginalDIY - Admin - Contacts');
  }

  ngOnInit(): void {
    this.retrieveTutorials();
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      this.currentUser = this.tokenStorageService.getUser();
      this.currentToken = this.tokenStorageService.getToken();
      console.log(this.users);
    }
  }

  retrieveTutorials(): void {
    this.userService.getAll()
      .subscribe({
        next: (data) => {
          this.users = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}
