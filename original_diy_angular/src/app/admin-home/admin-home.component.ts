import { Component, OnInit } from '@angular/core';
import { Title } from "@angular/platform-browser";

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit {
  constructor(private title: Title) {
    this.title.setTitle('OriginalDIY - Admin - Index');
  }

  ngOnInit(): void {
  }
}
