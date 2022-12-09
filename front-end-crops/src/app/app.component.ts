import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
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
