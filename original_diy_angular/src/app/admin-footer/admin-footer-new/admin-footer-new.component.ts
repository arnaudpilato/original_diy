import { Component, OnInit } from '@angular/core';
import { DiyFooter } from "../../model/footer.model";
import { AmazonS3Service } from "../../service/amazon-s3.service";
import { Title } from "@angular/platform-browser";
import { FooterService } from "../../service/footer.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-admin-footer-new',
  templateUrl: './admin-footer-new.component.html',
  styleUrls: ['./admin-footer-new.component.scss']
})
export class AdminFooterNewComponent implements OnInit {
  public footer: DiyFooter = new DiyFooter();
  public isSignUpFailed: boolean = false;
  public errorMessage: string = '';
  public selectedFiles: any;
  public nameFile: any = null;
  public file: any;
  public changeImage = false;
  public currentFileUpload: any;
  public message: string = '';

  constructor(
    private title: Title,
    private footerService: FooterService,
    private amazonS3Service: AmazonS3Service,
    private router: Router) {
      this.title.setTitle("OriginalDIY - admin - footer - new");
  }

  ngOnInit(): void {
  }

  selectFile(event: any) {
    this.selectedFiles = event.target.files;
    this.nameFile = this.selectedFiles.item(0).name;
    console.log(this.selectedFiles.item(0).name);
  }

  change(event: any) {
    this.changeImage = true;
  }

  onSubmit() {
    const data =  {
      name: this.footer.name,
      picturePath: this.nameFile,
      socialNetworkPath: this.footer.socialNetworkPath,
      visible: this.footer.visible,
    }

    if (this.selectedFiles != null) {
      this.currentFileUpload = this.selectedFiles.item(0);
      this.amazonS3Service.pushFileToStorage(this.currentFileUpload).subscribe(event => {
        this.selectedFiles = undefined;
      });
    }

    this.message = '';

    this.footerService.create(data).subscribe({
      next: (data) => {
        console.log(data);
        window.location.href="/admin-footer"
        //this.router.navigate(['/admin-footer']);
      },

      error: (err) => console.error(err)
    });
  }
}
