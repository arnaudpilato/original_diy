import { Component, OnInit } from '@angular/core';
import { DiyUser } from "../../model/user.model";
import { Title } from "@angular/platform-browser";
import { TokenStorageService } from "../../service/token-storage.service";
import { UserService } from "../../service/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import { DiyRole } from "../../model/role.model";
import {BadgeService} from "../../service/badge.service";
import {DiyBadge} from "../../model/badge.model";

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
  public badges: DiyBadge[] | undefined;
  public eRoles = DiyRole;
  public role: string[] = [];
  public all_selected_values: number[] = [];
  public searchBadge: string = "";
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  public badgeIds: DiyBadge[] | undefined;
  public currentUser: DiyUser = new DiyUser();

  constructor(
    private title: Title,
    private tokenStorageService: TokenStorageService,
    private userService: UserService,
    private route: ActivatedRoute,
    private badgeService: BadgeService,
    private router: Router) {
      this.title.setTitle('Modifier un contact');
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      this.currentUser = this.tokenStorageService.getUser();
      this.roles = this.currentUser.roles;
      this.getUser(this.route.snapshot.params["id"]);
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.getAllBadges();
    }
  }

  toggleCheckBox($event: any){
    return (this.all_selected_values.indexOf($event) != -1);
  }

  getUser(id: string): void {
    this.userService.getById(id).subscribe({
      next: (data) => {
        this.user = data;
        this.role[0] = this.user.roles[0].name == "ROLE_USER" ? "user" : "admin";
        this.badgeIds = data.badges;
        this.badgeIds?.forEach(value => {
          if (value.id != null) {
            this.all_selected_values.push(value.id);
          }
        })
      },

      error: (e) => console.error(e)
    });
  }

  onChange(value: any): void {
    if (this.all_selected_values.includes(value)) {
      this.all_selected_values = this.all_selected_values.filter((item) => item !== value);
    } else {
      this.all_selected_values.push(value);
    }

  }

  getAllBadges(): void {
    this.badgeService.getAll(this.searchBadge).subscribe({
      next: (data) => {
        this.badges = data;
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
      badgesSelected: this.all_selected_values,
    }

    this.userService.update(this.user.id, data).subscribe({
      next: (res) => {
        this.message = res.message ? res.message : 'Vos donn??es ont bien ??t?? mises ?? jour !';
        this.router.navigate(['/admin/contact'])
      },

      error: (e) => console.error(e)
    });
  }

  getRole(role: string) {
    this.role[0] = role;
  }
}
