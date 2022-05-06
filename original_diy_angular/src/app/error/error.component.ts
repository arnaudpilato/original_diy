import { Component, OnInit } from '@angular/core';
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.scss']
})
export class ErrorComponent implements OnInit {
  constructor(private title: Title) {
    this.title.setTitle('Erreur');
  }

  ngOnInit(): void {
  }
}
