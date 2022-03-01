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

  getById(id: any): Observable<DiyWorkshop> {
    return this.http.get(this.baseUrl + "get/" + id);
  }

  create(data: any): Observable<any> {
    return this.http.post(this.baseUrl + "new", data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(this.baseUrl + "edit/" + id, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(this.baseUrl + "/test/workshop/delete/" + id);
  }
}
