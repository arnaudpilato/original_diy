import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../service/token-storage.service";
import {DiyFooter} from "../../model/footer.model";
import {ActivatedRoute, Router} from "@angular/router";
import {FooterService} from "../../service/footer.service";

@Component({
  selector: 'app-admin-footer-edit',
  templateUrl: './admin-footer-edit.component.html',
  styleUrls: ['./admin-footer-edit.component.scss']
})
export class AdminFooterEditComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public socialNetwork: DiyFooter = new DiyFooter();
  public isSignUpFailed: boolean = false;
  public errorMessage: string = '';
  public selectedFiles: any;
  public nameFile: any = null;
  public changeImage = false;

  constructor(private tokenStorageService: TokenStorageService,
              private footerService: FooterService,
              private activatedRoute: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.getSocialNetwork(this.activatedRoute.snapshot.params['id']);
    }
  }

  getSocialNetwork(id: DiyFooter): void {
    this.footerService.getById(id).subscribe({
      next: (data) => {
        this.socialNetwork = data;
      },

      error: (err) => console.error(err)
    });
  }

  onSubmit() {
    const data =  {
      name: this.socialNetwork.name,
      socialNetworkPath: this.socialNetwork.socialNetworkPath,
      picturePath: this.socialNetwork.picturePath,
      visible: this.socialNetwork.visible
    }

    this.footerService.update(this.socialNetwork.id, data).subscribe({
      next: (data) => {
        this.router.navigate(['/admin/footer']);
      },

      error: (err) => {
        console.error(err);
        this.isSignUpFailed = true;
        this.errorMessage = err.error.message;
      }
    });
  }

  selectFile(event: any) {
    this.selectedFiles = event.target.files;
    this.nameFile = this.selectedFiles.item(0).name;
  }

  change(event: any) {
    this.changeImage = true;
  }
}
