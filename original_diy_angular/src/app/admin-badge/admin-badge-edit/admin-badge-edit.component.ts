import { Component, OnInit } from '@angular/core';
import {DiyBadge} from "../../model/badge.model";
import {TokenStorageService} from "../../service/token-storage.service";
import {ActivatedRoute, Route, Router} from "@angular/router";
import {BadgeService} from "../../service/badge.service";
import {AmazonS3Service} from "../../service/amazon-s3.service";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-admin-badge-edit',
  templateUrl: './admin-badge-edit.component.html',
  styleUrls: ['./admin-badge-edit.component.scss']
})
export class AdminBadgeEditComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public isSignUpFailed: boolean = false;
  public errorMessage: string = '';
  public badge: DiyBadge = new DiyBadge();
  public currentFileUpload: any;
  public selectedFiles: any;
  public nameFile: any = null;
  public changeImage = false;

  constructor(
      private tokenStorageService: TokenStorageService,
      private router: Router,
      private badgeService: BadgeService,
      private amazonS3Service: AmazonS3Service,
      private route: ActivatedRoute,
      private title: Title) {
    this.title.setTitle('Modifier un badge');
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.getBadge(this.route.snapshot.params["id"]);
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

  getBadge(id: string): void {
    this.badgeService.getById(id).subscribe({
      next: (data) => {
        this.badge = data;
        this.badge.step == 0 ? this.badge.condition = 'manual' : this.badge.condition = 'after';
        console.log(data);
      },

      error: (err) => console.error(err)
    });
  }

  onSubmit() {
    const data = {
      name: this.badge.name,
      picturePath: this.nameFile,
      description: this.badge.description,
      condition: this.badge.condition,
      step: this.badge.step
    };

    if (this.selectedFiles != null) {
      this.currentFileUpload = this.selectedFiles.item(0);
      this.amazonS3Service.pushFileToStorage(this.currentFileUpload).subscribe(event => {
        this.selectedFiles = undefined;
      });
    }

    this.badgeService.update(this.badge.id, data).subscribe({
      next: (data) => {
        console.log(data);
        window.location.href="/admin/badge";
      },

      error: (err) => console.error(err)
    });
  }
}
