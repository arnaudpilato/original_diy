import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {TokenStorageService} from "../service/token-storage.service";
import {Router} from "@angular/router";
import {WorkshopService} from "../service/workshop.service";
import {DiyWorkshop} from "../model/workshop.model";
import {CommentaryService} from "../service/commentary.service";

@Component({
  selector: 'app-admin-commentary',
  templateUrl: './admin-commentary.component.html',
  styleUrls: ['./admin-commentary.component.scss']
})
export class AdminCommentaryComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public workshops: DiyWorkshop[] | undefined;

  constructor(private title: Title, private tokenStorageService: TokenStorageService, private workshopService: WorkshopService, private commentaryService: CommentaryService, private router: Router) {
    this.title.setTitle('OriginalDIY - Admin - Contacts');
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.getAllWorkshop();
    }
  }

  getAllWorkshop(): void {
    this.workshopService.getAll().subscribe({
      next: (data) => {
        this.workshops = data;
      },

      error: (e) => console.error(e)
    });
  }

  confirm(id: number) {
    this.commentaryService.confirmed(id).subscribe({});
  }

  deleteCommentary(id: number) {
    console.log(id);
    this.commentaryService.delete(id).subscribe({
      next: () => {
        window.location.reload();
      }
    });
  }
}
