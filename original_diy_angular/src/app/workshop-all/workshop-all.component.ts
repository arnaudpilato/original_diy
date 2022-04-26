import {Component, OnInit} from '@angular/core';
import {WorkshopService} from "../service/workshop.service";
import {AmazonS3Service} from "../service/amazon-s3.service";

@Component({
  selector: 'app-workshop-all',
  templateUrl: './workshop-all.component.html',
  styleUrls: ['./workshop-all.component.scss']
})
export class WorkshopAllComponent implements OnInit {
  public workshops: any[] | undefined;
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';
  public static: string = '/assets/img/static-picture.png';

  constructor(
    private workshopService: WorkshopService,
    private amazonS3Service: AmazonS3Service
  ) {}

  ngOnInit(): void {
    this.getAllConfirmed()
  }

  private getAllConfirmed() {
    this.workshopService.getAllConfirmed().subscribe({
      next: (data) => {
        this.workshops = data;
        console.log(this.workshops);

      },

      error: (err) => console.log(err)
    });
  }
}
