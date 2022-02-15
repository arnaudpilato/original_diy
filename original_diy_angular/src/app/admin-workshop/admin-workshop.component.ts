import { Component, OnInit } from '@angular/core';
import {DiyWorkshop} from "../model/workshop.model";
import {Title} from "@angular/platform-browser";
import {WorkshopService} from "../service/workshop.service";

@Component({
  selector: 'app-admin-workshop',
  templateUrl: './admin-workshop.component.html',
  styleUrls: ['./admin-workshop.component.scss']
})
export class AdminWorkshopComponent implements OnInit {
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  public static: string = '/assets/img/static-picture.png';
  public workshops: DiyWorkshop[] | undefined;

  constructor(private title: Title, private workshopService: WorkshopService) {
    this.title.setTitle("OriginalDIY - Admin - Workshops")
  }

  ngOnInit(): void {
    this.getAllWorkshops();
  }

  getAllWorkshops(): void {
    this.workshopService.getAll().subscribe({
      next: (data) => {
        this.workshops = data;
        console.log(data);
      },

      error: (err) => console.log(err)
    });
  }
}
