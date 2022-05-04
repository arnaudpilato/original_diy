import { Component, OnInit } from '@angular/core';
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-user-workshop-index',
  templateUrl: './user-workshop-index.component.html',
  styleUrls: ['./user-workshop-index.component.scss']
})
export class UserWorkshopIndexComponent implements OnInit {
  constructor(private title: Title) {
    this.title.setTitle('Mes ateliers')
  }

  ngOnInit(): void {
  }
}
