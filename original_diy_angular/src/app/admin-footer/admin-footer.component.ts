import { Component, OnInit } from '@angular/core';
import { FooterService } from "../service/footer.service";
import { DiyFooter } from "../model/footer.model";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-admin-footer',
  templateUrl: './admin-footer.component.html',
  styleUrls: ['./admin-footer.component.scss']
})
export class AdminFooterComponent implements OnInit {
  public socialNetworks: DiyFooter[] | undefined;
  public static: string = '/assets/img/static-picture.png';
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  constructor(private title:Title, private footerService: FooterService) {
    this.title.setTitle("OriginalDIY - Admin - footer")
  }

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

  deleteSocialNetwork(id: any): void {
    this.footerService.delete(id).subscribe({
      next: (res) => {
        console.log(res);
        window.location.reload();
      },

      error: (e) => console.error(e)
    });
  }
}
