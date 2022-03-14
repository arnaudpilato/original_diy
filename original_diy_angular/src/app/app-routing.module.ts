import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";

import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";

import { AdminHomeComponent } from "./admin-home/admin-home.component";
import { AdminContactComponent } from "./admin-contact/admin-contact.component";
import { AdminContactNewComponent } from "./admin-contact/admin-contact-new/admin-contact-new.component";
import { AdminContactEditComponent } from "./admin-contact/admin-contact-edit/admin-contact-edit.component";
import { AdminWorkshopComponent } from "./admin-workshop/admin-workshop.component";
import { AdminWorkshopNewComponent } from "./admin-workshop/admin-workshop-new/admin-workshop-new.component";
import { AdminWorkshopEditComponent } from "./admin-workshop/admin-workshop-edit/admin-workshop-edit.component";
import { AdminFooterComponent } from "./admin-footer/admin-footer.component";
import { AdminFooterNewComponent } from "./admin-footer/admin-footer-new/admin-footer-new.component";
import { AdminFooterEditComponent } from "./admin-footer/admin-footer-edit/admin-footer-edit.component";
import { AdminBadgeComponent } from "./admin-badge/admin-badge.component";
import { AdminBadgeNewComponent } from "./admin-badge/admin-badge-new/admin-badge-new.component";

import { HomeComponent } from "./home/home.component";
import { MapComponent } from "./map/map.component";
import { WorkshopComponent } from "./workshop/workshop.component";
import { ProfileComponent } from "./profile/profile.component";

import { Error401Component } from "./error/error401/error401.component";
import { Error404Component } from "./error/error404/error404.component";
import { Error500Component } from "./error/error500/error500.component";
import { ErrorComponent } from "./error/error.component";
import {AdminBackgroundComponent} from "./admin-background/admin-background.component";



const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  { path: 'admin/home', component: AdminHomeComponent },
  { path: 'admin/contact', component: AdminContactComponent },
  { path: 'admin/contact/new', component: AdminContactNewComponent },
  { path: 'admin/contact/edit/:id', component: AdminContactEditComponent },
  { path: 'admin/workshop', component: AdminWorkshopComponent },
  { path: 'admin/workshop/new', component: AdminWorkshopNewComponent },
  { path: 'admin/workshop/edit/:id', component: AdminWorkshopEditComponent },
  { path: 'admin/footer', component: AdminFooterComponent },
  { path: 'admin/footer/new', component: AdminFooterNewComponent },
  { path: 'admin/footer/edit/:id', component: AdminFooterEditComponent },
  { path: 'admin/badge', component: AdminBadgeComponent },
  { path: 'admin/badge/new', component: AdminBadgeNewComponent },
  { path: 'admin/background', component: AdminBackgroundComponent },

  { path: 'error', component: ErrorComponent },
  { path: 'error/401', component: Error401Component },
  { path: 'error/404', component: Error404Component },
  { path: 'error/500', component: Error500Component },

  { path: 'home', component: HomeComponent },
  { path: 'workshop/:id', component: WorkshopComponent },
  { path: 'map', component: MapComponent },
  { path: 'profile', component: ProfileComponent },

  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
