import { Injectable } from '@angular/core';
import { environment } from "../../environments/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import { DiyBadge } from "../model/badge.model";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BadgeService {
  baseUrl = environment.baseUrl + '/test/badge';

  constructor(private http: HttpClient) { }

  getAll(searchBadge: string): Observable<DiyBadge[]> {
    let params = new HttpParams()
        .set('searchBadge', searchBadge)
    return this.http.get<DiyBadge[]>(this.baseUrl + "/all", {params});
  }
  getById(id: any): Observable<DiyBadge> {
    return this.http.get(this.baseUrl + "/get/" + id);
  }

  create(data: any): Observable<any> {
    return this.http.post(this.baseUrl + "/new", data)
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(this.baseUrl + "/edit/" + id, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(this.baseUrl + "/delete/" + id)
  }
}
