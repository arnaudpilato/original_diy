import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import {DiyUser} from "../model/user.model";

const API_URL = 'http://localhost:8080/api/test/user/';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  getAll(): Observable<DiyUser[]> {
    return this.http.get<DiyUser[]>(API_URL + "all");
  }

  getById(id: any): Observable<DiyUser> {
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
