import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EmailModel} from "../model/email.model";
import {password} from "../model/password.model";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {

  public token: string = this.route.snapshot.queryParams['token'];
  changePassword: boolean = false;
  passwordFail: boolean = false;
  public model: any = new password();
  passwordConfirm: string | undefined;

  constructor(private route: ActivatedRoute, private userService: UserService) {
  }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.model.password +" et "+this.passwordConfirm)
    if (this.model.password == this.passwordConfirm) {
      const data: any = {
        password: this.model.password,
        token: this.token
      }
      this.userService.updatePassword(data).subscribe({
        next: (data) => {
          this.changePassword = true;
          window.setTimeout(function () {
            window.location.href = "/home";
          }, 1000)
        },

        error: (e) => console.log(e + "e")
      });
    } else {
      this.passwordFail = true;
    }
  }
}
