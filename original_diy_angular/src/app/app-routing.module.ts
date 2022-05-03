import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";

import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";

import { ForgotPasswordComponent } from "./forgot-password/forgot-password.component";
import { ResetPasswordComponent } from "./reset-password/reset-password.component";

import { AdminBadgeComponent } from "./admin-badge/admin-badge.component";
import { AdminBadgeEditComponent } from "./admin-badge/admin-badge-edit/admin-badge-edit.component";
import { AdminBadgeNewComponent } from "./admin-badge/admin-badge-new/admin-badge-new.component";
import { AdminCommentaryComponent } from "./admin-commentary/admin-commentary.component";
import { AdminContactComponent } from "./admin-contact/admin-contact.component";
import { AdminContactEditComponent } from "./admin-contact/admin-contact-edit/admin-contact-edit.component";
import { AdminContactNewComponent } from "./admin-contact/admin-contact-new/admin-contact-new.component";
import { AdminFooterComponent } from "./admin-footer/admin-footer.component";
import { AdminFooterEditComponent } from "./admin-footer/admin-footer-edit/admin-footer-edit.component";
import { AdminFooterNewComponent } from "./admin-footer/admin-footer-new/admin-footer-new.component";
import { AdminHomeComponent } from "./admin-home/admin-home.component";
import { AdminReservationComponent } from "./admin-reservation/admin-reservation.component";
import { AdminWorkshopComponent } from "./admin-workshop/admin-workshop.component";
import { AdminWorkshopEditComponent } from "./admin-workshop/admin-workshop-edit/admin-workshop-edit.component";
import { AdminWorkshopNewComponent } from "./admin-workshop/admin-workshop-new/admin-workshop-new.component";

import { AddWorkshopComponent } from "./add-workshop/add-workshop.component";
import { HomeComponent } from "./home/home.component";
import { MapComponent } from "./map/map.component";
import { ProfileComponent } from "./profile/profile.component";
import { UserWorkshopIndexComponent } from "./user-workshop-index/user-workshop-index.component";
import { WorkshopAllComponent } from "./workshop-all/workshop-all.component";
import { WorkshopComponent } from "./workshop/workshop.component";
import { WorkshopProfileComponent } from "./workshop-profile/workshop-profile.component";
import { WorkshopReservationComponent } from "./workshop-reservation/workshop-reservation.component";

import { ErrorComponent } from "./error/error.component";
import { Error401Component } from "./error/error401/error401.component";
import { Error404Component } from "./error/error404/error404.component";
import { Error500Component } from "./error/error500/error500.component";
import {ContactUsComponent} from "./contact-us/contact-us.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: 'reset-password', component: ResetPasswordComponent },

  { path: 'admin/badge', component: AdminBadgeComponent },
  { path: 'admin/badge/new', component: AdminBadgeNewComponent },
  { path: 'admin/badge/edit/:id', component: AdminBadgeEditComponent },
  { path: 'admin/commentary', component: AdminCommentaryComponent },
  { path: 'admin/contact', component: AdminContactComponent },
  { path: 'admin/contact/new', component: AdminContactNewComponent },
  { path: 'admin/contact/edit/:id', component: AdminContactEditComponent },
  { path: 'admin/footer', component: AdminFooterComponent },
  { path: 'admin/footer/new', component: AdminFooterNewComponent },
  { path: 'admin/footer/edit/:id', component: AdminFooterEditComponent },
  { path: 'admin/home', component: AdminHomeComponent },
  { path: 'admin/reservation', component: AdminReservationComponent },
  { path: 'admin/workshop', component: AdminWorkshopComponent },
  { path: 'admin/workshop/new', component: AdminWorkshopNewComponent },
  { path: 'admin/workshop/edit/:id', component: AdminWorkshopEditComponent },

  { path: 'error', component: ErrorComponent },
  { path: 'error/401', component: Error401Component },
  { path: 'error/404', component: Error404Component },
  { path: 'error/500', component: Error500Component },

  { path: 'atelier-utilisateur', component: WorkshopProfileComponent },
  { path: 'creation-atelier', component: AddWorkshopComponent },
  { path: 'home', component: HomeComponent },
  { path: 'les-ateliers', component: WorkshopAllComponent },
  { path: 'map', component: MapComponent },
  { path: 'mes-ateliers', component: UserWorkshopIndexComponent },
  { path: 'mes-inscriptions', component: WorkshopReservationComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'workshop/:id', component: WorkshopComponent },
  { path: 'contact', component: ContactUsComponent },

  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
