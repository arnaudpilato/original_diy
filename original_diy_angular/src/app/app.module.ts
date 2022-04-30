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
import { AdminFooterEditComponent } from './admin-footer/admin-footer-edit/admin-footer-edit.component';
import { AdminFooterNewComponent } from './admin-footer/admin-footer-new/admin-footer-new.component';
import { WorkshopComponent } from './workshop/workshop.component';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { nl2brPipe } from './nl2br.pipe';
import { AdminBadgeNewComponent } from './admin-badge/admin-badge-new/admin-badge-new.component';
import { AdminBadgeComponent } from "./admin-badge/admin-badge.component";
import { Error401Component } from './error/error401/error401.component';
import { AdminBackgroundComponent } from './admin-background/admin-background.component';
import { WorskhopProfilComponent } from './worskhop-profil/worskhop-profil.component';
import { AddWorkshopComponent } from './add-workshop/add-workshop.component';
import { CommentaryComponent } from './commentary/commentary.component';
import { UserWorkshopIndexComponent } from './user-workshop-index/user-workshop-index.component';
import { AdminCommentaryComponent } from './admin-commentary/admin-commentary.component';
import { WorkshopReservationComponent } from './workshop-reservation/workshop-reservation.component';
import { AdminBadgeEditComponent } from './admin-badge/admin-badge-edit/admin-badge-edit.component';
import {TruncatePipe} from "./TruncatePipe";
import { AdminReservationComponent } from './admin-reservation/admin-reservation.component';
import { WorkshopAllComponent } from './workshop-all/workshop-all.component';
import {NgxPaginationModule} from "ngx-pagination";


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
    AdminFooterComponent,
    AdminFooterEditComponent,
    AdminFooterNewComponent,
    WorkshopComponent,
    nl2brPipe,
    AdminBadgeNewComponent,
    AdminBadgeComponent,
    Error401Component,
    AdminBackgroundComponent,
    WorskhopProfilComponent,
    AddWorkshopComponent,
    CommentaryComponent,
    UserWorkshopIndexComponent,
    AdminCommentaryComponent,
    WorkshopReservationComponent,
    AdminBadgeEditComponent,
    TruncatePipe,
    AdminReservationComponent,
    WorkshopAllComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    CKEditorModule,
    NgxPaginationModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
