import { Component, OnInit } from '@angular/core';
import { Title } from "@angular/platform-browser";
import { UserService } from "../../service/user.service";
import { DiyUser } from "../../model/user.model";
import { Router } from "@angular/router";
import {TokenStorageService} from "../../service/token-storage.service";

@Component({
  selector: 'app-admin-contact-new',
  templateUrl: './admin-contact-new.component.html',
  styleUrls: ['./admin-contact-new.component.scss']
})
export class AdminContactNewComponent implements OnInit {
  public roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public isSignUpFailed: boolean = false;
  public errorMessage: string = '';
  public model: DiyUser = new DiyUser();
  public role: string[] = ['ROLE_USER'];

  constructor(
      private tokenStorageService: TokenStorageService,
      private title: Title,
      private userService: UserService,
      private router: Router) {
    this.title.setTitle('Ajouter un contact');
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
    }
  }

  getRole(role: string) {
    this.role[0] = role;
  }

  onSubmit() {
    const data = {
      username: this.model.username,
      email: this.model.email,
      password: this.model.password,
      firstName: this.model.firstName,
      lastName: this.model.lastName,
      phone: this.model.phone,
      roles: this.role,
    };

    this.userService.create(data).subscribe({
      next: (data) => {
        this.router.navigate(['/admin/contact']);
      },

      error: (e) => {
        console.error(e)
        this.isSignUpFailed = true;
        this.errorMessage = e.error.message;
      }
    });
  }
}
