import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public form: any = {
    username: null,
    password: null
  };

  public isLoggedIn: boolean = false;
  public isLoginFailed: boolean = false;
  public errorMessage: string = '';
  public roles: string[] = [];
  public userName: string = '';

  constructor() { }

  ngOnInit(): void {
  }

}
