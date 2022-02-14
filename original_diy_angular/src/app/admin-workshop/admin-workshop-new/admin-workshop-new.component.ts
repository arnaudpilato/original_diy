import { Component, OnInit } from '@angular/core';
import { DiyWorkshop } from "../../model/workshop.model";
import { Title } from "@angular/platform-browser";
import { WorkshopService } from "../../service/workshop.service";
import { Router } from "@angular/router";
import {AmazonS3Service} from "../../service/amazon-s3.service";

@Component({
  selector: 'app-admin-workshop-new',
  templateUrl: './admin-workshop-new.component.html',
  styleUrls: ['./admin-workshop-new.component.scss']
})
export class AdminWorkshopNewComponent implements OnInit {
 public isSignUpFailed: boolean = false;
   public errorMessage: string = '';
  /*public currentFileUpload: any;
  public selectedFiles: any;
  public nameFile: any = null;*/
  public model: DiyWorkshop = new DiyWorkshop();
  /*public file: any;
  public changeImage = false;*/

  constructor(private title: Title, private workshopService: WorkshopService,/* private amazonS3Service: AmazonS3Service, private router: Router*/) {
    this.title.setTitle("OriginalDIY - Admin - Workshop - New");
  }

  ngOnInit(): void {
  }
/*
  selectFile(event: any) {
    this.selectedFiles = event.target.files;
    this.nameFile = this.selectedFiles.item(0).name;
    console.log(this.selectedFiles.item(0).name);
  }
  change(event: any) {
    this.changeImage = true;
  }*/

  onSubmit() {
    const data = {
      title: this.model.title,
    }
      /*picturePath: this.nameFile,
     treetNumber: this.model.streetNumber,
     street: this.model.street,
     postCode: this.model.postCode,
     city: this.model.city,
     description: this.model.description,
     comments: this.model.comments,
     longitude: this.model.longitude,
     latitude: this.model.latitude,
     confirmation: this.model.confirmation,*/

    console.log(data);

/*
    if (this.selectedFiles != null) {
      this.currentFileUpload = this.selectedFiles.item(0);
      this.amazonS3Service.pushFileToStorage(this.currentFileUpload).subscribe(event => {
        this.selectedFiles = undefined;
      });
    }
*/
    this.workshopService.create(data).subscribe({
      next: (res) => {
        console.log(res);
        //this.router.navigate(['/admin-workshop']);
      },

      error: (e) => {
        console.error(e)
        this.isSignUpFailed = true;
        this.errorMessage = e.error.message;
      }
    });
  }
}
