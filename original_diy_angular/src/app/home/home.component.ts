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

  constructor(private title: Title, private userService: UserService) {
    this.title.setTitle("OriginalDIY - Accueil");
  }

  ngOnInit(): void {
    this.userService.getPublicContent().subscribe({
      next: data => {
        this.content = data;
      },
      error: err => {
        this.content = JSON.parse(err.error).message;
      }
    });
  }
}
