import { Component, OnInit } from '@angular/core';
import {UserService} from "../service/user.service";
import {EmailModel} from "../model/email.model";

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {
  public model: any = new EmailModel();
  mailSend: boolean = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    const data: any = {
      email: this.model.email
    }
    this.userService.updateMail(data).subscribe({
      next: (data) => {
        this.mailSend = true;
        window.setTimeout(function() {
          window.location.href = "/home";
        }, 1000)
        },

      error: (e) => console.log(e+"e")
    });
  }
}
