import { Component, OnInit } from '@angular/core';
import {EmailModel} from "../model/email.model";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.scss']
})
export class ContactUsComponent implements OnInit {
  public mailSend: boolean = false;
  public model: any = new EmailModel();

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    const data: any = {
      email: this.model.email,
      message: this.model.message,
    }
    this.userService.contact(data).subscribe({
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
