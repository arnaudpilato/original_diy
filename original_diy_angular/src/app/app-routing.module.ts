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

import {HomeComponent} from "./home/home.component";
import { MapComponent } from "./map/map.component";

import { Error404Component } from "./error/error404/error404.component";
import { Error500Component } from "./error/error500/error500.component";
import { ErrorComponent } from "./error/error.component";
import { ProfileComponent } from "./profile/profile.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  { path: 'admin/home', component: AdminHomeComponent },
  { path: 'admin/contact', component: AdminContactComponent },
  { path: 'admin/contact/new', component: AdminContactNewComponent },
  { path: 'admin/contact/edit/:id', component: AdminContactEditComponent },
  { path: 'admin/workshop', component: AdminWorkshopComponent },
  { path: 'admin/workshop/new', component: AdminWorkshopNewComponent},
  { path: 'admin/workshop/edit', component: AdminWorkshopEditComponent },
  { path: 'admin/footer', component: AdminFooterComponent},
  { path: 'admin/footer/new', component: AdminFooterNewComponent},
  { path: 'admin/footer/edit/:id', component: AdminFooterEditComponent},

  { path: 'error', component: ErrorComponent},
  { path: 'error/404', component: Error404Component },
  { path: 'error/500', component: Error500Component },

  { path: 'home', component: HomeComponent },
  { path: 'map', component: MapComponent },
  { path: 'profile/:id', component: ProfileComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
