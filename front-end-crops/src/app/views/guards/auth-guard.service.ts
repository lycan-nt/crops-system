import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ) : Observable<boolean> | Promise<boolean> | boolean {
    let isAuthenticated = this.authService.isAuthentication();
    if (isAuthenticated) {
      return true;
    } 
    else {
      this.router.navigateByUrl('/login');
      return false;
    }
  }

}
