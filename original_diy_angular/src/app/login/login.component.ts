import { Component, OnInit } from '@angular/core';
import { Title } from "@angular/platform-browser";
import { AuthService } from "../service/auth.service";
import { TokenStorageService } from "../service/token-storage.service";

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

  constructor(private title: Title, private authService: AuthService, private tokenStorageService: TokenStorageService) {
    this.title.setTitle("OriginalDIY - Connexion")
}

  ngOnInit(): void {
    if (this.tokenStorageService.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorageService.getUser().roles;
      this.userName = this.tokenStorageService.getUser().username;
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;

    this.authService.login(username, password).subscribe({
      next: data => {
        this.tokenStorageService.saveToken(data.accessToken);
        this.tokenStorageService.saveUser(data.user);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorageService.getUser().roles;
        this.reloadPage();
      }
    });
  }

  reloadPage(): void {
    window.location.reload();
  }
}
