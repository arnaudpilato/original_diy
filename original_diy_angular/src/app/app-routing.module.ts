import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";

import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { AdminHomeComponent } from "./admin-home/admin-home.component";
import { MapComponent } from "./map/map.component";
import {AdminContactComponent} from "./admin-contact/admin-contact.component";


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'admin-home', component: AdminHomeComponent },
  { path: 'admin-contact', component: AdminContactComponent},
  { path: 'map', component: MapComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
