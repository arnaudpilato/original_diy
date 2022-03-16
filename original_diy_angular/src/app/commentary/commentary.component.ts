import {Component, OnInit} from '@angular/core';
import {DiyComment} from "../model/commentary.model";
import {TokenStorageService} from "../service/token-storage.service";
import {WorkshopService} from "../service/workshop.service";
import {ActivatedRoute} from "@angular/router";
import {CommentaryService} from "../service/commentary.service";

@Component({
  selector: 'app-commentary',
  templateUrl: './commentary.component.html',
  styleUrls: ['./commentary.component.scss']
})
export class CommentaryComponent implements OnInit {
  public comments: any = new DiyComment();

  constructor(private commentaryService: CommentaryService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getCommentaryByWorkshop(this.route.snapshot.params["id"]);
  }

  private getCommentaryByWorkshop(id: any): void {
    this.commentaryService.getCommentaryByWorkshop(id).subscribe({
      next: (data) => {
        this.comments = data;
        console.log("comment : ", data);
      },
      error: (err) => console.error(err)
    })
  }
}
