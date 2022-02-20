import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { FooterComponent } from './footer/footer.component';

import { authInterceptorProviders } from "./helper/auth.interceptor";
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from "./app-routing.module";
import { FormsModule } from "@angular/forms";
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { HttpClientModule } from "@angular/common/http";
import { MapComponent } from './map/map.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AdminContactComponent } from './admin-contact/admin-contact.component';
import { AdminContactEditComponent } from './admin-contact/admin-contact-edit/admin-contact-edit.component';
import { AdminContactNewComponent } from './admin-contact/admin-contact-new/admin-contact-new.component';
import { AdminWorkshopComponent } from './admin-workshop/admin-workshop.component';
import { AdminWorkshopNewComponent } from './admin-workshop/admin-workshop-new/admin-workshop-new.component';
import { AdminWorkshopEditComponent } from './admin-workshop/admin-workshop-edit/admin-workshop-edit.component';
import { Error404Component } from './error/error404/error404.component';
import { Error500Component } from './error/error500/error500.component';
import { ErrorComponent } from './error/error.component';
import { ProfileComponent } from './profile/profile.component';
import { AdminFooterComponent } from './admin-footer/admin-footer.component';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    NavBarComponent,
    FooterComponent,
    LoginComponent,
    HomeComponent,
    MapComponent,
    AdminHomeComponent,
    AdminContactComponent,
    AdminContactEditComponent,
    AdminContactNewComponent,
    AdminWorkshopComponent,
    AdminWorkshopNewComponent,
    AdminWorkshopEditComponent,
    Error404Component,
    Error500Component,
    ErrorComponent,
    ProfileComponent,
    AdminFooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
