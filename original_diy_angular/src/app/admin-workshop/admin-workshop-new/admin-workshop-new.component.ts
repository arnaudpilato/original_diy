import { Component, OnInit } from '@angular/core';
import { DiyWorkshop } from "../../model/workshop.model";
import { Title } from "@angular/platform-browser";
import { WorkshopService } from "../../service/workshop.service";
import { Router } from "@angular/router";
import {AmazonS3Service} from "../../service/amazon-s3.service";
import {TokenStorageService} from "../../service/token-storage.service";

@Component({
  selector: 'app-admin-workshop-new',
  templateUrl: './admin-workshop-new.component.html',
  styleUrls: ['./admin-workshop-new.component.scss']
})
export class AdminWorkshopNewComponent implements OnInit {
  public isSignUpFailed: boolean = false;
  public errorMessage: string = '';
  public currentFileUpload: any;
  public selectedFiles: any;
  public nameFile: any = null;
  public model: DiyWorkshop = new DiyWorkshop();
  public file: any;
  public changeImage = false;
  public authuser: any;

  constructor(private title: Title, private workshopService: WorkshopService, private amazonS3Service: AmazonS3Service,
              private router: Router, private token: TokenStorageService) {
    this.title.setTitle("OriginalDIY - Admin - Workshop - New");

  }

  ngOnInit(): void {
    this.authuser = this.token.getUser();
    console.log("console log de this.authuser: ", this.authuser)
  }

  selectFile(event: any) {
    this.selectedFiles = event.target.files;
    this.nameFile = this.selectedFiles.item(0).name;
    console.log(this.selectedFiles.item(0).name);
  }

  change(event: any) {
    this.changeImage = true;
  }

  onSubmit() {
    const data : any = {
      title: this.model.title,
      picturePath: this.nameFile,
      streetNumber: this.model.streetNumber,
      street: this.model.street,
      postCode: this.model.postCode,
      city: this.model.city,
      description: this.model.description,
      confirmation: this.model.confirmation,
      date: new Date((new Date(this.model.date)).getTime() + (60 * 60 * 24 * 1000)),
      diyUser: this.authuser,


    /*
     comments: this.model.comments,
     longitude: this.model.longitude,
     latitude: this.model.latitude,
     */
    }

    if (this.selectedFiles != null) {
      this.currentFileUpload = this.selectedFiles.item(0);
      this.amazonS3Service.pushFileToStorage(this.currentFileUpload).subscribe(event => {
        this.selectedFiles = undefined;
      });
    }

    if (this.selectedFiles != null) {
      this.currentFileUpload = this.selectedFiles.item(0);
      this.amazonS3Service.pushFileToStorage(this.currentFileUpload).subscribe(event => {
        this.selectedFiles = undefined;
      });
    }

    this.workshopService.create(data).subscribe({
      next: (res) => {
        this.router.navigate(['/admin-workshop']);
      },
      error: (e) => {
        this.isSignUpFailed = true;
        this.errorMessage = e.error.message;
      }
    });
  }
}
