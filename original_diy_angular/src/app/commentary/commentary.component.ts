import {Component, OnInit} from '@angular/core';
import {DiyComment} from "../model/commentary.model";
import {ActivatedRoute} from "@angular/router";
import {CommentaryService} from "../service/commentary.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-commentary',
  templateUrl: './commentary.component.html',
  styleUrls: ['./commentary.component.scss']
})
export class CommentaryComponent implements OnInit {
  public comments: any = new DiyComment();
  public model: any = new DiyComment();
  public diyWorkshopId: number | undefined;

  constructor(private commentaryService: CommentaryService, private route: ActivatedRoute, private router: Router,
) {

  }

  ngOnInit(): void {
    this.diyWorkshopId = this.route.snapshot.params["id"];
    this.getCommentaryByWorkshop(this.diyWorkshopId);
  }

  private getCommentaryByWorkshop(id: any): void {
    this.commentaryService.getCommentaryByWorkshop(id).subscribe({
      next: (data) => {
        this.comments = data;
      },
      error: (err) => console.error(err)
    })
  }

  onSubmit() {
    const data: any = {
      comment: this.model.comment,
      diyWorkshopId: this.diyWorkshopId,
    }
        console.log("data : ",data)
    this.commentaryService.create(data).subscribe({
      next: (data) => {
        console.log(data)
        window.location.reload();
      },
      error: (e) => {
      }
    });

  }

}