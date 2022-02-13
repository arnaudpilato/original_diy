import { Component, OnInit } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {UserService} from "../service/user.service";
import {DiyUser} from "../model/user.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin-contact-new',
  templateUrl: './admin-contact-new.component.html',
  styleUrls: ['./admin-contact-new.component.scss']
})
export class AdminContactNewComponent implements OnInit {
  public isSignUpFailed: boolean = false;
  public errorMessage: string = '';
  public model: DiyUser = new DiyUser();

  constructor(private title: Title, private userService: UserService, private router: Router) {
    this.title.setTitle("OriginalDIY - Admin - contact - new")
  }

  ngOnInit(): void {
  }

  onSubmit() {
    const data = {
      username: this.model.username,
      email: this.model.email,
      password: this.model.password,
      firstName: this.model.firstName,
      lastName: this.model.lastName,
      roles: this.model.roles,
    };

    this.userService.create(data).subscribe({
      next: (res) => {
        console.log(res);
        this.router.navigate(['/admin-contact']);
        }, error: (e) => {
        console.error(e)
        this.isSignUpFailed = true;
        this.errorMessage = e.error.message;
      }
    });
  }

}
