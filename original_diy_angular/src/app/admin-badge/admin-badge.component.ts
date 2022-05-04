import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../service/token-storage.service";
import {Router} from "@angular/router";
import {BadgeService} from "../service/badge.service";
import {DiyBadge} from "../model/badge.model";
import {AmazonS3Service} from "../service/amazon-s3.service";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-admin-badge',
  templateUrl: './admin-badge.component.html',
  styleUrls: ['./admin-badge.component.scss']
})
export class AdminBadgeComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public badges: DiyBadge[] | undefined;
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  public searchBadge: string = "";

  constructor(
      private tokenStorageService: TokenStorageService,
      private router: Router,
      private badgeService: BadgeService,
      public amazonS3Service: AmazonS3Service,
      public title: Title) {
    this.title.setTitle('Gestion des badges')
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.getAllBadges();
    }
  }

  getAllBadges(): void {
    this.badgeService.getAll(this.searchBadge).subscribe({
      next: (data) => {
        this.badges = data;
        console.log(this.searchBadge);
      },

      error: (e) => console.error(e)
    });
  }

  deleteBadge(id: any, path: any): void {
    this.amazonS3Service.deleteFile(path);

    this.badgeService.delete(id).subscribe({
      next: (res) => {
        window.location.reload();
      },

      error: (e) => console.error(e)
    });
  }
}
