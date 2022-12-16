import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LocalStorageService } from 'ngx-webstorage';
import { LoginPayload } from './login-payload';
import { map, Observable } from 'rxjs';
import { JwtAuthResponse } from './jwt-auth-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = "http://localhost:8080/api/auth/";

  constructor(
    private httpClient: HttpClient,
    private localStorageService: LocalStorageService
  ) { }

  login(loginPayload: LoginPayload) :  Observable<boolean> {
    return this.httpClient.post<JwtAuthResponse>(this.url + 'login', loginPayload).pipe(map(data => {
      this.localStorageService.store('authenticationToken', data.authenticationToken);
      this.localStorageService.store('userName', data.userName);
      return true;
    }));
  }

}
