import { Component, OnInit } from '@angular/core';
import {DiyUser} from "../../model/user.model";
import {Title} from "@angular/platform-browser";
import {TokenStorageService} from "../../service/token-storage.service";
import {UserService} from "../../service/user.service";
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
  public message: string = '';
  public user: DiyUser = new DiyUser();

  public form: any = {
    username: this.user.username,
    firstName: null,
    lastName: null,
    phone: null,
    email: null,
    roles: null,
  }

  constructor(private title: Title, private tokenStorageService: TokenStorageService, private userService: UserService, private route: ActivatedRoute) {
    this.title.setTitle("OriginalDIY - Admin - contact - edit")
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    console.log(this.user)

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
    this.message = '';

    this.userService.update(this.user.id, this.form)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'Vos données ont bien été mises à jour !';
        },
        error: (e) => console.error(e)
      });
  }
}
