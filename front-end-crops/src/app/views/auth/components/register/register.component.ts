import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user: string= "";
  password: string= "";
  confirm_password: string= "";

  register() {

  }
}
