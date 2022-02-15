import { Component, OnInit } from '@angular/core';
import {DiyWorkshop} from "../../model/workshop.model";
import {DiyUser} from "../../model/user.model";
import {Title} from "@angular/platform-browser";
import {TokenStorageService} from "../../service/token-storage.service";
import {UserService} from "../../service/user.service";
import {ActivatedRoute} from "@angular/router";
import {WorkshopService} from "../../service/workshop.service";
import {AmazonS3Service} from "../../service/amazon-s3.service";

@Component({
  selector: 'app-admin-workshop-edit',
  templateUrl: './admin-workshop-edit.component.html',
  styleUrls: ['./admin-workshop-edit.component.scss']
})
export class AdminWorkshopEditComponent implements OnInit {
  public isSignUpFailed: boolean = false;
  public errorMessage: string = '';
  public currentFileUpload: any;
  public selectedFiles: any;
  public nameFile: any = null;
  public isLoggedIn: boolean = false;
  public currentUser: any;
  public currentToken: any;
  public message: string = '';
  public workshop: DiyWorkshop = new DiyWorkshop();
  public file: any;
  public changeImage = false;

  public form: any = {
    title: this.workshop.title,
    picturePath: null,
    streetNumber: null,
    street: null,
    postCode: null,
    city: null,
    description: null,
    comments: null,
    longitude: null,
    latitude: null,
    confirmation: null
  }

  constructor(
    private title: Title,
    private tokenStorageService: TokenStorageService,
    private workshopService: WorkshopService,
    private amazonS3Service: AmazonS3Service,
    private route: ActivatedRoute
  ) {
    this.title.setTitle("OriginalDIY - Admin - workshop - edit")
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    console.log(this.workshop)

    if (this.isLoggedIn) {
      this.getWorkshop(this.route.snapshot.params["id"]);
      this.currentUser = this.tokenStorageService.getUser();
      this.currentToken = this.tokenStorageService.getToken();
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

  getWorkshop(id: string): void {
    this.workshopService.getById(id).subscribe({
      next: (data) => {
        this.workshop = data;
        console.log(data);
      },

      error: (err) => console.error(err)
    });
  }

  onSubmit() {
    console.log(data);

    if (this.selectedFiles != null) {
      this.currentFileUpload = this.selectedFiles.item(0);
      this.amazonS3Service.pushFileToStorage(this.currentFileUpload).subscribe(event => {
        this.selectedFiles = undefined;
      });
    }

    this.message = '';

    this.workshopService.update(this.workshop.id, this.form).subscribe({
      next: (data) => {
        console.log(data);
        this.message = data.message ? data.message : 'Vos données ont bien été mises à jour !';
      },

      error: (err) => console.error(err)
    });
  }
}
