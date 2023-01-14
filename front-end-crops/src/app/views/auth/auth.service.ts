import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LocalStorageService } from 'ngx-webstorage';
import { LoginPayload } from './login-payload';
import { map, Observable } from 'rxjs';
import { JwtAuthResponse } from './jwt-auth-response';
import { JwtHelperService } from '@auth0/angular-jwt';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = "http://localhost:8080/api/auth/";
  private jwtHelper = new JwtHelperService()
  constructor(
    private httpClient: HttpClient,
    private localStorageService: LocalStorageService,
    private _snackBar: MatSnackBar
  ) { }

  login(loginPayload: LoginPayload): Observable<boolean> {
    return this.httpClient.post<JwtAuthResponse>(this.url + 'login', loginPayload).pipe(map(data => {
      this.localStorageService.store('authenticationToken', data.authenticationToken);
      this.localStorageService.store('userName', data.userName);
      return true;
    }));
  }

  isAuthentication(): boolean {

    if (this.localStorageService.retrieve("userName") != null
      && !this.jwtHelper.isTokenExpired(this.localStorageService.retrieve('authenticationToken'))) {
      return true;
    } else {
      this.localStorageService.clear('authenticationToken');
      this.localStorageService.clear('userName');
      return false;
    }

  }

  logout() {
    this.localStorageService.clear('authenticationToken');
    this.localStorageService.clear('userName');
  }

}
