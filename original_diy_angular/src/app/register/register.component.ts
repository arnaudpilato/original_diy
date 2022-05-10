import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {AuthService} from "../service/auth.service";
import {UserService} from "../service/user.service";

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
  public errorUsername: boolean = false;
  private usernames: any = [];

  constructor(
    private title: Title,
    private authService: AuthService,
    private userService: UserService,
  ) {
    this.title.setTitle('Inscription');
  }

  ngOnInit(): void {
    this.getAllUsers();
  }

  onSubmit(): void {
    const {username, email, password} = this.form;
console.log(username);
    if (!this.usernames.includes(username)) {
      this.authService.register(username, email, password).subscribe({
        next: data => {
          this.isSuccessful = true;
          this.isSignUpFailed = false;
          window.setTimeout(function () {
            window.location.href = "/home";
          }, 1000);
        },

        error: err => {
          this.errorMessage = err.message;
          this.isSignUpFailed = true;
        }
      })
    }else{
      this.errorUsername = true;
    }
  }

  getAllUsers(): void {
    this.userService.getAllUser().subscribe({
      next: (data) => {
        const datas = data;
        this.usernames = datas.users.map((el: { username: any; }) => el.username);
      },
      error: (e) => console.error(e)
    });
  }

}
