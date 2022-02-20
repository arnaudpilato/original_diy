import { Component, OnInit } from '@angular/core';
import {FooterService} from "../service/footer.service";
import {DiyFooter} from "../model/footer.model";

@Component({
  selector: 'app-admin-footer',
  templateUrl: './admin-footer.component.html',
  styleUrls: ['./admin-footer.component.scss']
})
export class AdminFooterComponent implements OnInit {
  public socialNetworks: DiyFooter | undefined;
  constructor(private footerService: FooterService) { }

  ngOnInit(): void {
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
