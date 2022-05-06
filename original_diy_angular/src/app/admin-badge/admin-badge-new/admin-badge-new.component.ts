import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from "../../service/token-storage.service";
import { Router } from "@angular/router";
import { DiyBadge } from "../../model/badge.model";
import { BadgeService } from "../../service/badge.service";
import { AmazonS3Service } from "../../service/amazon-s3.service";
import {UserService} from "../../service/user.service";
import {DiyUser} from "../../model/user.model";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-admin-badge-new',
  templateUrl: './admin-badge-new.component.html',
  styleUrls: ['./admin-badge-new.component.scss']
})
export class AdminBadgeNewComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public isSignUpFailed: boolean = false;
  public errorMessage: string = '';
  public model: DiyBadge = new DiyBadge();
  public users: DiyUser[] | undefined;
  public currentFileUpload: any;
  public selectedFiles: any;
  public nameFile: any = null;
  public changeImage = false;

  constructor(
      private tokenStorageService: TokenStorageService,
      private router: Router,
      private badgeService: BadgeService,
      private amazonS3Service: AmazonS3Service,
      private userService: UserService,
      private title: Title) {
    this.title.setTitle('Ajouter un badge');
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.model.condition = 'manual';
    }
  }

  selectFile(event: any) {
    this.selectedFiles = event.target.files;
    this.nameFile = this.selectedFiles.item(0).name;
    console.log("image du s3 : " + this.selectedFiles.item(0).name);
  }

  change(event: any) {
    this.changeImage = true;
  }

  onSubmit() {
    const data = {
      name: this.model.name,
      picturePath: this.nameFile,
      description: this.model.description,
      condition: this.model.condition,
      step: this.model.step,
    };

    if (this.selectedFiles != null) {
      this.currentFileUpload = this.selectedFiles.item(0);
      this.amazonS3Service.pushFileToStorage(this.currentFileUpload).subscribe(event => {
        this.selectedFiles = undefined;
      });
    }

    this.badgeService.create(data).subscribe({
      next: (data) => {
        console.log(data)
        window.location.href="/admin/badge";
      },

      error: (e) => {
        console.error(e)
        this.isSignUpFailed = true;
        this.errorMessage = e.error.message;
      }
    });
  }
}
