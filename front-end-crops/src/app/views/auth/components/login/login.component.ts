import { Component } from '@angular/core';
import { LoginPayload } from '../../login-payload';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginPayload: LoginPayload = new LoginPayload();

  login() {
    if (this.loginPayload.userName == "admin" && this.loginPayload.password == "admin") {
      alert("Admin Login");
    } else {
      alert("User Login")
    }
  }


}
