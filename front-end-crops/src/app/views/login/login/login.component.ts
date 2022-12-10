import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  title = 'front-end-crops';
  user: string= "";
  password: string= "";
  confirm_password: string= "";


  login() {
    if (this.user == "admin" && this.password == "admin") {
      alert("Admin Login");
    } else {
      alert("User Login")
    }
  }

  register() {

  }
}
