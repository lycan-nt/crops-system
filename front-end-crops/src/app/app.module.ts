import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import {MatCardModule} from '@angular/material/card';
import {MatTabsModule} from '@angular/material/tabs';
import {MatInputModule} from '@angular/material/input';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { LoginComponent } from './views/auth/components/login/login.component';
import { RegisterComponent } from './views/auth/components/register/register.component';
import { AuthComponent } from './views/auth/auth.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgxWebstorageModule } from 'ngx-webstorage';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { HeaderComponent } from './views/header/header.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatTooltipModule} from '@angular/material/tooltip';
import { AuthGuardService } from './views/guards/auth-guard.service';
import { NavComponent } from './views/nav/nav.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { HomeComponent } from './views/home/home.component';
import { EventsComponent } from './views/events/events.component';
import { HttpClientInterceptor } from './views/guards/http-client-interceptor';
import {MaterialModule} from "../material-module";
import {MatFormFieldModule} from '@angular/material/form-field';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AuthComponent,
    HeaderComponent,
    NavComponent,
    HomeComponent,
    EventsComponent
  ],
  imports: [
    MaterialModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MatFormFieldModule,
    NgxWebstorageModule.forRoot(),
    MatIconModule,
    MatListModule,
    MatTooltipModule,
    MatToolbarModule,
    MatSnackBarModule,
    MatButtonModule,
    MatInputModule,
    MatTabsModule,
    MatCardModule,
    MatSidenavModule,
    RouterModule.forRoot([
      {path: 'header', component: HeaderComponent},
      {path: 'login', component: AuthComponent},
      {path: 'home', component: HomeComponent, canActivate: [AuthGuardService]},
      {path: 'events', component: EventsComponent, canActivate: [AuthGuardService]}

    ]),
    BrowserAnimationsModule
  ],
  providers: [AuthGuardService, {provide: HTTP_INTERCEPTORS, useClass: HttpClientInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
