import { Component, OnInit } from '@angular/core';
import { DiyWorkshop } from "../model/workshop.model";
import { Title } from "@angular/platform-browser";
import { WorkshopService } from "../service/workshop.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-admin-workshop-new',
  templateUrl: './admin-workshop-new.component.html',
  styleUrls: ['./admin-workshop-new.component.scss']
})
export class AdminWorkshopNewComponent implements OnInit {
  public isSignUpFailed: boolean = false;
  public errorMessage: string = '';
  public model: DiyWorkshop = new DiyWorkshop();

  constructor(private title: Title, private workshopService: WorkshopService, private router: Router) {
    this.title.setTitle("OriginalDIY - Admin - Workshop - New");
  }

  ngOnInit(): void {
  }

  onSubmit() {
    const data = {
      title: this.model.title,
      /*picture: this.model.picture,
      streetNumber: this.model.streetNumber,
      street: this.model.street,
      postCode: this.model.postCode,
      city: this.model.city,
      description: this.model.description,
      comments: this.model.comments,
      longitude: this.model.longitude,
      latitude: this.model.latitude,
      confirmation: this.model.confirmation,*/
    };

    this.workshopService.create(data).subscribe({
      next: (res) => {
        console.log(res);
      },

      error: (e) => {
        console.error(e)
        this.isSignUpFailed = true;
        this.errorMessage = e.error.message;
      }
    });
  }
}
