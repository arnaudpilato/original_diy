import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {TokenStorageService} from "../service/token-storage.service";
import {WorkshopService} from "../service/workshop.service";
import {DiyWorkshop} from "../model/workshop.model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-workshop',
  templateUrl: './workshop.component.html',
  styleUrls: ['./workshop.component.scss']
})
export class WorkshopComponent implements OnInit {
  public workshop: any = new DiyWorkshop();
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  public static: string = '/assets/img/static-picture.png';


  constructor(private title: Title, private tokenStorageService: TokenStorageService,
              private workshopService: WorkshopService, private route: ActivatedRoute) {
    this.title.setTitle("OriginalDIY - Atelier");
  }

  ngOnInit(): void {
      this.getWorkshop(this.route.snapshot.params["id"]);
  }

  getWorkshop(id: number): void {
    this.workshopService.getByIdHome(id).subscribe({
      next: (data) => {
        this.workshop = data;

        console.log(data);
      },

      error: (err) => console.error(err)
    });
  }

}
