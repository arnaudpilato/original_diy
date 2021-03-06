import { Component, OnInit } from '@angular/core';
import {DiyWorkshop} from "../../model/workshop.model";
import {Title} from "@angular/platform-browser";
import {TokenStorageService} from "../../service/token-storage.service";
import {ActivatedRoute, Router} from "@angular/router";
import {WorkshopService} from "../../service/workshop.service";
import {AmazonS3Service} from "../../service/amazon-s3.service";
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import {CategoryService} from "../../service/category.service";

@Component({
  selector: 'app-admin-workshop-edit',
  templateUrl: './admin-workshop-edit.component.html',
  styleUrls: ['./admin-workshop-edit.component.scss']
})
export class AdminWorkshopEditComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public isSignUpFailed: boolean = false;
  public errorMessage: string = '';
  public currentFileUpload: any;
  public selectedFiles: any;
  public nameFile: any = null;
  public message: string = '';
  public workshop: DiyWorkshop = new DiyWorkshop();
  public file: any;
  public changeImage = false;
  public Editor = ClassicEditor;
  public subCategoryId: number | undefined;
  public categories: any[] | undefined;

  constructor(
    private title: Title,
    private tokenStorageService: TokenStorageService,
    private workshopService: WorkshopService,
    private amazonS3Service: AmazonS3Service,
    private route: ActivatedRoute,
    private router: Router,
    private categoryService: CategoryService
  ) {
    this.title.setTitle('Modifier un atelier');
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      this.getWorkshop(this.route.snapshot.params["id"]);
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
    }
    this.getAllCategories();
  }

  selectFile(event: any) {
    this.selectedFiles = event.target.files;
    this.nameFile = this.selectedFiles.item(0).name;
  }

  change(event: any) {
    this.changeImage = true;
  }

  getWorkshop(id: string): void {
    this.workshopService.getById(id).subscribe({
      next: (data) => {
        this.workshop = data;
        this.subCategoryId = this.workshop.subCategory.id;
      },
      error: (err) => console.error(err)
    });
  }

  onSubmit() {
    const data =  {
      title: this.workshop.title,
      picturePath: this.nameFile,
      streetNumber: this.workshop.streetNumber,
      limitedPlaces: this.workshop.limitedPlaces,
      street: this.workshop.street,
      postCode: this.workshop.postCode,
      city: this.workshop.city,
      description: this.workshop.description,
      comments: this.workshop.comments,
      longitude: this.workshop.longitude,
      latitude: this.workshop.latitude,
      confirmation: this.workshop.confirmation,
      date: new Date((new Date(this.workshop.date)).getTime() + (0 * 0 * 0)),
      subCategoryId: this.subCategoryId,
    }

    if (this.selectedFiles != null) {
      this.currentFileUpload = this.selectedFiles.item(0);
      this.amazonS3Service.pushFileToStorage(this.currentFileUpload).subscribe(event => {
        this.selectedFiles = undefined;
      });
    }

    this.message = '';

    this.workshopService.update(this.workshop.id, data).subscribe({
      next: (data) => {
        this.router.navigate(['/admin/workshop']);
      },

      error: (err) => console.error(err)
    });
  }

  getAllCategories(): any {
    this.categoryService.getAll().subscribe({
        next: (datas) => {
          this.categories = datas;
          this.categories.forEach((category) => {
          })
        },
        error: (e) => console.log(e)
      }
    )
  }
}
