import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { DiyWorkshop } from "../model/workshop.model";

const API_URL = 'http://localhost:8080/api/test/workshop/';

@Injectable({
  providedIn: 'root'
})
export class WorkshopService {
  constructor(private http: HttpClient) { }

  getAll(): Observable<DiyWorkshop[]> {
    return this.http.get<DiyWorkshop[]>(API_URL + "all");
  }

  getById(id: any): Observable<DiyWorkshop> {
    return this.http.get(API_URL + "get/" + id);
  }

  create(data: any): Observable<any> {
    return this.http.post(API_URL + "new", data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(API_URL + "edit/" + id, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(API_URL + "delete/" + id);
  }
}
