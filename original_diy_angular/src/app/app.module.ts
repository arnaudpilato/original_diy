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
    AdminWorkshopComponent
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
