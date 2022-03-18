import { Component, OnInit } from '@angular/core';
import {DiyFooter} from "../../model/footer.model";
import {TokenStorageService} from "../../service/token-storage.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DiyBackground} from "../../model/background.model";
import {BackgroundService} from "../../service/background.service";
import {AmazonS3Service} from "../../service/amazon-s3.service";

@Component({
  selector: 'app-admin-background-edit',
  templateUrl: './admin-background-edit.component.html',
  styleUrls: ['./admin-background-edit.component.scss']
})
export class AdminBackgroundEditComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public background: DiyBackground = new DiyBackground();
  public isSignUpFailed: boolean = false;
  public errorMessage: string = '';
  public selectedFiles: any;
  public nameFile: any = null;
  public changeImage = false;
  public currentFileUpload: any;public name:any;

  constructor(private tokenStorageService: TokenStorageService,
              private backgroundService: BackgroundService,
              private activatedRoute: ActivatedRoute,
              private amazonS3Service: AmazonS3Service,
              private router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.getBackground(this.activatedRoute.snapshot.params['id']);
    }
  }

  getBackground(id: DiyFooter): void {
    this.backgroundService.getById(id).subscribe({
      next: (data) => {
        this.background = data;
        console.log(data);
      },

      error: (err) => console.error(err)
    });
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

    this.backgroundService.update(this.background.id, data).subscribe({
      next: (data) => {
        console.log("data");
        window.location.href="/admin/background"
      },

      error: (err) => {
        console.error(err);
        this.isSignUpFailed = true;
        this.errorMessage = err.error.message;
      }
    });
  }

  selectFile(event: any) {
    this.selectedFiles = event.target.files;
    this.nameFile = this.selectedFiles.item(0).name;
    console.log(this.selectedFiles.item(0).name);
  }

  change(event: any) {
    this.changeImage = true;
  }

  deleteBackground() {
    this.backgroundService.delete(this.background.id).subscribe({
      next: (res) => {
        console.log(res);
        window.location.href="/admin/background";
      },
    })
  }
}
