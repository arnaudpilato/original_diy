import { Component, OnInit } from '@angular/core';
import { Title } from "@angular/platform-browser";
import { UserService } from "../service/user.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  public content: string | undefined;

  constructor(private title: Title) {
    this.title.setTitle("OriginalDIY - Accueil");
  }

  ngOnInit(): void {
  }
}
