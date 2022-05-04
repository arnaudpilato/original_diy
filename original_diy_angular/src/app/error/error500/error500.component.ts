import { Component, OnInit } from '@angular/core';
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-error500',
  templateUrl: './error500.component.html',
  styleUrls: ['./error500.component.scss']
})
export class Error500Component implements OnInit {
  constructor(private title: Title) {
    this.title.setTitle('Erreur 500');
  }

  ngOnInit(): void {
  }
}
