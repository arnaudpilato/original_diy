import { Component, OnInit } from '@angular/core';
import {DiyUser} from "../model/user.model";
import {Title} from "@angular/platform-browser";
import {TokenStorageService} from "../service/token-storage.service";
import {UserService} from "../service/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-admin-contact-edit',
  templateUrl: './admin-contact-edit.component.html',
  styleUrls: ['./admin-contact-edit.component.scss']
})
export class AdminContactEditComponent implements OnInit {
  public isLoggedIn: boolean = false;
  public currentUser: any;
  public currentToken: any;
  public user: DiyUser = {
    username: '',
    firstName: '',
    lastName: '',
  }

  public form: any = {
    username: null,
    firstName: null,
    lastName: null,
    phone: null,
    email: null,
    role: null,
  }

  constructor(private title: Title, private tokenStorageService: TokenStorageService, private userService: UserService, private route: ActivatedRoute) {
    this.title.setTitle("OriginalDIY - Admin - contact - edit")
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      this.getUser(this.route.snapshot.params["id"]);
      this.currentUser = this.tokenStorageService.getUser();
      this.currentToken = this.tokenStorageService.getToken();
    }
  }

  getUser(id: string): void {
    this.userService.getById(id)
      .subscribe({
        next: (data) => {
          this.user = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  onSubmit() {

  }
}
