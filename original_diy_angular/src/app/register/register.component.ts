import {Component, OnInit} from '@angular/core';
import { Title } from "@angular/platform-browser";
import { AuthService } from "../service/auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  public form: any = {
    username: null,
    email: null,
    password: null,
  }

  public isSuccessful: boolean = false;
  public isSignUpFailed: boolean = false;
  public errorMessage: string = '';

  constructor(private title: Title, private authService: AuthService) {
    this.title.setTitle("OriginalDIY - Inscription")
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const {username, email, password} = this.form;

    this.authService.register(username, email, password).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },

      error: err => {
        this.errorMessage = err.message;
        this.isSignUpFailed = true;
      }
    })
  }
}
