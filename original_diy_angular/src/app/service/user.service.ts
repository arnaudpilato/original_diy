import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import {DiyUser} from "../model/user.model";
import { environment } from "../../environments/environment";
import {UserPagination} from "../model/userPagination";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = environment.baseUrl + '/test/user/';

  constructor(private http: HttpClient) { }

  getAll(params: any): Observable<any> {
    return this.http.get<any>(this.baseUrl + "all", {params});
  }

  getById(id: any): Observable<DiyUser> {
    return this.http.get(this.baseUrl + "get/" + id);
  }

  create(data: any): Observable<any> {
    return this.http.post(this.baseUrl + "new", data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(this.baseUrl + "edit/" + id, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(this.baseUrl + "delete/" + id);
  }

  updateMail(data: any): Observable<any> {
    return this.http.put(this.baseUrl + "recoverPassword", data);
  }

  updatePassword(data: any): Observable<any> {
    return this.http.put(this.baseUrl + "newPassword", data)
  }
}
