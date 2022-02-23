import { Component, OnInit } from '@angular/core';
import { FooterService } from "../service/footer.service";
import {DiyFooter} from "../model/footer.model";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {
  public socialNetworks: DiyFooter[] | undefined;
  public static: string = '/assets/img/static-picture.png';
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  constructor(private footerService: FooterService) { }

  ngOnInit(): void {
    this.getAllSocialNetworks();
  }

  getAllSocialNetworks(): void {
    this.footerService.getAll().subscribe({
      next: (data) => {
        this.socialNetworks = data;
        console.log(data);
      },

      error: (err) => console.error(err)
    });
  }
}
