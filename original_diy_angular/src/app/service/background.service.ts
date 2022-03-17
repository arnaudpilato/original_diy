import { Injectable } from '@angular/core';
import { environment } from "../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { DiyBackground } from "../model/background.model";

@Injectable({
  providedIn: 'root'
})
export class BackgroundService {
  baseUrl = environment.baseUrl + '/test/background/';

  constructor(private http: HttpClient) { }

  getAll(): Observable<DiyBackground[]> {
    return this.http.get<DiyBackground[]>(this.baseUrl + "all");
  }

  getAllWithoutVisibility(): Observable<DiyBackground[]> {
    return this.http.get<DiyBackground[]>(this.baseUrl + "visibility");
  }

  getById(id: any): Observable<DiyBackground> {
    return this.http.get(this.baseUrl + "get/" + id);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(this.baseUrl + "delete/" + id);
  }

  create(data: any): Observable<any> {
    return this.http.post(this.baseUrl + "new", data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(this.baseUrl + "edit/" + id, data);
  }
}
