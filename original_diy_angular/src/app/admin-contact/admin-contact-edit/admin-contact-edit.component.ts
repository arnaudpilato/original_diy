import { Component, OnInit } from '@angular/core';
import { DiyUser } from "../../model/user.model";
import { Title } from "@angular/platform-browser";
import { TokenStorageService } from "../../service/token-storage.service";
import { UserService } from "../../service/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import { DiyRole } from "../../model/role.model";

@Component({
  selector: 'app-admin-contact-edit',
  templateUrl: './admin-contact-edit.component.html',
  styleUrls: ['./admin-contact-edit.component.scss']
})
export class AdminContactEditComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public message: string = '';
  public user: DiyUser = new DiyUser();
  public eRoles = DiyRole;
  public role: string[] = [];

  constructor(
    private title: Title,
    private tokenStorageService: TokenStorageService,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router) {
      this.title.setTitle("OriginalDIY - Admin - contact - edit")
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.getUser(this.route.snapshot.params["id"]);
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
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
      roles: this.role,
      password: this.user.password,
    }

    this.userService.update(this.user.id, data).subscribe({
      next: (res) => {

        this.message = res.message ? res.message : 'Vos données ont bien été mises à jour !';
        this.router.navigate(['/admin/contact'])
      },

      error: (e) => console.error(e)
    });
  }

  getRole(role: string) {
    this.role[0] = role;
  }
}
