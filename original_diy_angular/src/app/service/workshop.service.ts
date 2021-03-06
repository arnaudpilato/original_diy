import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { DiyWorkshop } from "../model/workshop.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class WorkshopService {

  baseUrl = environment.baseUrl + '/test/workshop/';

  constructor(private http: HttpClient) { }

  getAll(): Observable<DiyWorkshop[]> {
    return this.http.get<DiyWorkshop[]>(this.baseUrl + "all");
  }

  getAllConfirmed(): Observable<DiyWorkshop[]> {
    return this.http.get<DiyWorkshop[]>(this.baseUrl + "allConfirmed");
  }

  getWorkshopByUserId(): Observable<DiyWorkshop[]> {
    return this.http.get<DiyWorkshop[]>(this.baseUrl + "worskhop-by-user");
  }

  getLastWorkshop(): Observable<DiyWorkshop[]> {
    return this.http.get<DiyWorkshop[]>(this.baseUrl + "last-workshops");
  }

  getReservationByCurrentUser(): Observable<any> {
    return this.http.get(this.baseUrl + "reservation-by-current-user");
  }

  getById(id: any): Observable<DiyWorkshop> {
    return this.http.get(this.baseUrl + "get/" + id);
  }

  getByIdHome(id: any): Observable<DiyWorkshop> {
    return this.http.get(this.baseUrl + "get-atelier/" + id);
  }

  create(data: any): Observable<any> {
    return this.http.post(this.baseUrl + "new", data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(this.baseUrl + "edit/" + id, data);
  }

  reservation(id: any, data: any): Observable<any> {
    return this.http.patch(this.baseUrl + "worskhop/reservation/" + id, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(this.baseUrl + "delete/" + id);
  }

  deleteReservation(id: any): Observable<any> {
    return this.http.delete(this.baseUrl + "reservation/delete/" + id);
  }

  deleteReservationByUsername(username: any, workshopId: any): Observable<any> {
    return this.http.delete(this.baseUrl + "reservation/deleteByUsername/" + username + "/" + workshopId)
  }

}
