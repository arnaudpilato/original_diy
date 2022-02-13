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
