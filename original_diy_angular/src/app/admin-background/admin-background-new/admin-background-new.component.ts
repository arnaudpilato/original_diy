import { Component, OnInit } from '@angular/core';
import {DiyBackground} from "../../model/background.model";
import {TokenStorageService} from "../../service/token-storage.service";
import {BackgroundService} from "../../service/background.service";
import {Title} from "@angular/platform-browser";
import {AmazonS3Service} from "../../service/amazon-s3.service";

@Component({
  selector: 'app-admin-background-new',
  templateUrl: './admin-background-new.component.html',
  styleUrls: ['./admin-background-new.component.scss']
})
export class AdminBackgroundNewComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public background: DiyBackground = new DiyBackground();
  public isSignUpFailed: boolean = false;
  public errorMessage: string = '';
  public selectedFiles: any;
  public nameFile: any = null;
  public file: any;
  public name:any;
  public changeImage = false;
  public currentFileUpload: any;
  public message: string = '';
  public backgroundsWithoutVisibility: DiyBackground[] | undefined;

  constructor(
      private tokenStorageService: TokenStorageService,
      private title: Title,
      private backgroundService: BackgroundService,
      private amazonS3Service: AmazonS3Service) {
    this.title.setTitle("OriginalDIY - admin - background - new");
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    this.getAllBackgroundsWithoutVisibility();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
    }
  }

  selectFile(event: any) {
    this.selectedFiles = event.target.files;
    this.nameFile = this.selectedFiles.item(0).name;
    console.log(this.selectedFiles.item(0).name);
  }

  change(event: any) {
    this.changeImage = true;
  }

  nameBackground(name: any) {
    this.name = name;
    console.log(this.name);
  }

  onSubmit() {
    const data =  {
      name: this.name,
      picturePath: this.nameFile,
    }

    if (this.selectedFiles != null) {
      this.currentFileUpload = this.selectedFiles.item(0);
      this.amazonS3Service.pushFileToStorage(this.currentFileUpload).subscribe(event => {
        this.selectedFiles = undefined;
      });
    }

    this.message = '';

    this.backgroundService.create(data).subscribe({
      next: (data) => {
        console.log(data);
        window.location.href="/admin/background"
      },

      error: (err) => console.error(err)
    });
  }

  getAllBackgroundsWithoutVisibility() {
    this.backgroundService.getAllWithoutVisibility().subscribe( {
      next: (data) => {
        this.backgroundsWithoutVisibility = data;
      },

      error: (err => console.log(err))
    })
  }
}
