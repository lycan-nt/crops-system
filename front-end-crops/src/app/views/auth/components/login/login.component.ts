import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../../auth.service';
import { LoginPayload } from '../../login-payload';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginPayload: LoginPayload = new LoginPayload();

  constructor(
    private authService: AuthService,
    private route: Router,
    private _snackBar: MatSnackBar
  ) { }

  login() {

    this.authService.login(this.loginPayload).subscribe({
      next: data => {
        this.route.navigateByUrl("/home");
        this._snackBar.open("Login Sucess.", "Ok", {duration: 5000});
      },
      error: erro => {
        this.loginPayload.userName = "";
        this.loginPayload.password = "";
        this._snackBar.open("Invalid password or login", "Ok", 
        {
          duration: 5000,
          panelClass: 'snack-error'
        });
      }

    })
  }

}
