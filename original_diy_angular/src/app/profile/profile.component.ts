import { Component, OnInit } from '@angular/core';
import {DiyUser} from "../model/user.model";
import {Title} from "@angular/platform-browser";
import {TokenStorageService} from "../service/token-storage.service";
import {UserService} from "../service/user.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  public isLoggedIn: boolean = false;
  public currentUser: any;
  public currentToken: any;
  public message: string = '';
  public user: DiyUser = new DiyUser();

  constructor(
    private title: Title,
    private tokenStorageService: TokenStorageService,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router) {
    this.title.setTitle("OriginalDIY - profil")
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      this.currentUser = this.tokenStorageService.getUser();
      this.currentToken = this.tokenStorageService.getToken();
      this.getUser(this.currentUser.id);
    }
  }

  getUser(id: string): void {
    this.userService.getById(id).subscribe({
      next: (data) => {
        this.user = data;
      },

      error: (e) => console.error(e)
    });
  }

  onSubmit() {
    this.message = '';

    const data = {
      username: this.user.username,
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      phone: this.user.phone,
      email: this.user.email,
      password: this.user.password,
      birthday: this.user.birthday
    }

    this.userService.update(this.user.id, data).subscribe({
      next: (res) => {

        this.message = res.message ? res.message : 'Vos données ont bien été mises à jour !';
        this.router.navigate(['/home'])
      },

      error: (e) => console.error(e)
    });
  }
}
