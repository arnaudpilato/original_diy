import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';

import { authInterceptorProviders } from "./helper/auth.interceptor";

import { NavBarComponent } from './nav-bar/nav-bar.component';
import { FooterComponent } from './footer/footer.component';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';

import { AppRoutingModule } from "./app-routing.module";
import { BrowserModule } from '@angular/platform-browser';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { NgxPaginationModule } from "ngx-pagination";
import { nl2brPipe } from './nl2br.pipe';
import { TruncatePipe } from "./TruncatePipe";

import { AdminBadgeComponent } from "./admin-badge/admin-badge.component";
import { AdminBadgeEditComponent } from './admin-badge/admin-badge-edit/admin-badge-edit.component';
import { AdminBadgeNewComponent } from './admin-badge/admin-badge-new/admin-badge-new.component';
import { AdminCommentaryComponent } from './admin-commentary/admin-commentary.component';
import { AdminContactComponent } from './admin-contact/admin-contact.component';
import { AdminContactNewComponent } from './admin-contact/admin-contact-new/admin-contact-new.component';
import { AdminContactEditComponent } from './admin-contact/admin-contact-edit/admin-contact-edit.component';
import { AdminFooterComponent } from './admin-footer/admin-footer.component';
import { AdminFooterEditComponent } from './admin-footer/admin-footer-edit/admin-footer-edit.component';
import { AdminFooterNewComponent } from './admin-footer/admin-footer-new/admin-footer-new.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AdminReservationComponent } from './admin-reservation/admin-reservation.component';
import { AdminWorkshopComponent } from './admin-workshop/admin-workshop.component';
import { AdminWorkshopEditComponent } from './admin-workshop/admin-workshop-edit/admin-workshop-edit.component';
import { AdminWorkshopNewComponent } from './admin-workshop/admin-workshop-new/admin-workshop-new.component';

import { AddWorkshopComponent } from './add-workshop/add-workshop.component';
import { CommentaryComponent } from './commentary/commentary.component';
import { HomeComponent } from './home/home.component';
import { MapComponent } from './map/map.component';
import { ProfileComponent } from './profile/profile.component';
import { UserWorkshopIndexComponent } from './user-workshop-index/user-workshop-index.component';
import { WorkshopAllComponent } from './workshop-all/workshop-all.component';
import { WorkshopComponent } from './workshop/workshop.component';
import { WorkshopProfileComponent } from './workshop-profile/workshop-profile.component';
import { WorkshopReservationComponent } from './workshop-reservation/workshop-reservation.component';

import { ErrorComponent } from './error/error.component';
import { Error401Component } from './error/error401/error401.component';
import { Error404Component } from './error/error404/error404.component';
import { Error500Component } from './error/error500/error500.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent,
    nl2brPipe,
    TruncatePipe,
    AdminBadgeComponent,
    AdminBadgeEditComponent,
    AdminBadgeNewComponent,
    AdminCommentaryComponent,
    AdminContactComponent,
    AdminContactEditComponent,
    AdminContactNewComponent,
    AdminFooterComponent,
    AdminFooterEditComponent,
    AdminFooterNewComponent,
    AdminHomeComponent,
    AdminReservationComponent,
    AdminWorkshopComponent,
    AdminWorkshopEditComponent,
    AdminWorkshopNewComponent,
    AddWorkshopComponent,
    CommentaryComponent,
    HomeComponent,
    MapComponent,
    ProfileComponent,
    UserWorkshopIndexComponent,
    WorkshopAllComponent,
    WorkshopComponent,
    WorkshopProfileComponent,
    WorkshopReservationComponent,
    ErrorComponent,
    Error401Component,
    Error404Component,
    Error500Component
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    CKEditorModule,
    FormsModule,
    HttpClientModule,
    NgxPaginationModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
