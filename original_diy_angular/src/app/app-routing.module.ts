import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";

import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";

import { AdminHomeComponent } from "./admin-home/admin-home.component";
import { AdminContactComponent } from "./admin-contact/admin-contact.component";
import { AdminContactNewComponent } from "./admin-contact/admin-contact-new/admin-contact-new.component";
import { AdminContactEditComponent } from "./admin-contact/admin-contact-edit/admin-contact-edit.component";

import {HomeComponent} from "./home/home.component";
import { MapComponent } from "./map/map.component";
import {AdminWorkshopComponent} from "./admin-workshop/admin-workshop.component";
import {AdminWorkshopNewComponent} from "./admin-workshop-new/admin-workshop-new.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  { path: 'admin-home', component: AdminHomeComponent },
  { path: 'admin-contact', component: AdminContactComponent },
  { path: 'admin-contact-new', component: AdminContactNewComponent },
  { path: 'admin-contact-edit/:id', component: AdminContactEditComponent },
  { path: 'admin-workshop', component: AdminWorkshopComponent },
  { path: 'admin-workshop-new', component: AdminWorkshopNewComponent},

  { path: 'home', component: HomeComponent },
  { path: 'map', component: MapComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
