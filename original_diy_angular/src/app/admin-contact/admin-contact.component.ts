import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
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
  public users: DiyUser[] = [];
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';

  constructor(
      private title: Title,
      private tokenStorageService:TokenStorageService,
      private userService:UserService,
      private router: Router) {
    this.title.setTitle('Gestion des contacts');
  }

  public page = 1;
  public count = 0;
  public pageSize = 5;

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.getAllUsers();
    }
  }

  getRequestParams(page: number, pageSize: number): any {
    let params: any = {};
    if (page) {
      params[`page`] = page - 1;
    }
    if (pageSize) {
      params[`size`] = pageSize;
    }
    return params;
  }

  config = {
    id: 'custom',
    itemsPerPage: 5,
    currentPage: 1,
    totalItems: 5
  };

  getAllUsers(): void {
    const params = this.getRequestParams(this.page, this.pageSize);
    console.log("valeur du param " + params);
    this.userService.getAll(params).subscribe({
      next: (data) => {
        this.users = data.users;
        this.count = data.totalItems;
        console.log(data.users);
        console.log(this.users)
        console.log("page actuelle " + data.currentPage)
        console.log("taille de la page " + data.totalItems)
        console.log("Nombre de pages " + data.totalPages)
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

  pageChanged(event: number) {
    this.page = event;
    this.getAllUsers();
  }
}
