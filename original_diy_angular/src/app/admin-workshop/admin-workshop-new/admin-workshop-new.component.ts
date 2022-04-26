import {Component, OnInit} from '@angular/core';
import {DiyWorkshop} from "../../model/workshop.model";
import {Title} from "@angular/platform-browser";
import {WorkshopService} from "../../service/workshop.service";
import {Router} from "@angular/router";
import {AmazonS3Service} from "../../service/amazon-s3.service";
import {TokenStorageService} from "../../service/token-storage.service";
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import {categorieService} from "../../service/categorie.service";

@Component({
  selector: 'app-admin-workshop-new',
  templateUrl: './admin-workshop-new.component.html',
  styleUrls: ['./admin-workshop-new.component.scss'],

})
export class AdminWorkshopNewComponent implements OnInit {
  private roles: string[] = [];
  public isLoggedIn: boolean = false;
  public showAdminBoard: boolean = false;
  public isSignUpFailed: boolean = false;
  public errorMessage: string = '';
  public currentFileUpload: any;
  public selectedFiles: any;
  public nameFile: any = null;
  public model: any = new DiyWorkshop();
  public file: any;
  public changeImage = false;
  public authuser: any;
  public minDatetimeLocal: any;
  public subCategoryId: number | undefined;
  public Editor = ClassicEditor;
  public categories: any[] | undefined;



  constructor(
    private title: Title,
    private workshopService: WorkshopService,
    private amazonS3Service: AmazonS3Service,
    private router: Router,
    private token: TokenStorageService,
    private categoryService: categorieService) {
    this.title.setTitle("OriginalDIY - Admin - Workshop - New");
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.token.getToken();
    this.getAllCategories()
    this.Editor.defaultConfig = {
      toolbar: {
        items: [
          'heading', '|', 'bold', 'italic', 'link',
          'bulletedList', 'numberedList', '|', 'indent', 'outdent', '|',

          'blockQuote',
          'insertTable',
          'undo', 'redo'
        ]
      },
      language: 'fr',
      image: {
        toolbar: [
          'imageTextAlternative',
          'imageStyle:full',
          'imageStyle:side'
        ]
      }
    }
    if (this.isLoggedIn) {
      const user = this.token.getUser();
      this.authuser = this.token.getUser().user;
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.minDatetimeLocal = new Date();
      console.log("console log de this.authuser: ", this.authuser);
    }
  }

  selectFile(event: any) {
    this.selectedFiles = event.target.files;
    this.nameFile = this.selectedFiles.item(0).name;
    console.log("image du s3 : " + this.selectedFiles.item(0).name);
  }

  change(event: any) {
    this.changeImage = true;
  }

  onSubmit() {
    const data: any = {
      title: this.model.title,
      picturePath: this.nameFile,
      streetNumber: this.model.streetNumber,
      limitedPlaces: this.model.limitedPlaces,
      street: this.model.street,
      postCode: this.model.postCode,
      city: this.model.city,
      description: this.model.description,
      confirmation: this.model.confirmation,
      date: new Date((new Date(this.model.date)).getTime() + (60 * 60 * 1000)),
      diyUser: this.authuser,
      subCategoryId: this.subCategoryId,

    }
    console.log("data : "+data)

    if (this.selectedFiles != null) {
      this.currentFileUpload = this.selectedFiles.item(0);
      this.amazonS3Service.pushFileToStorage(this.currentFileUpload).subscribe(event => {
        this.selectedFiles = undefined;
      });
    }

    this.workshopService.create(data).subscribe({
      next: (data) => {
        console.log(data)
        this.router.navigate(['/admin/workshop']);
      },
      error: (e) => {
        this.isSignUpFailed = true;
        this.errorMessage = e.error.message;
      }
    });
  }
  getAllCategories(): any {
    this.categoryService.getAll().subscribe({
        next: (datas) => {
          console.log(datas)
          this.categories = datas;
          this.categories.forEach((category) => {
            console.log("category = " + category.subCategory)
          })

        },

        error: (e) => console.log(e)
      }
    )
  }
}
