import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { DiyFooter } from "../model/footer.model";
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class FooterService {
  baseUrl = environment.baseUrl + '/test/footer/';

  constructor(private http: HttpClient) { }

  getAll(): Observable<DiyFooter[]> {
    return this.http.get<DiyFooter[]>(this.baseUrl + "all");
  }

  delete(id: any): Observable<any> {
    return this.http.delete(this.baseUrl + "delete/" + id);
  }

  create(data: any): Observable<any> {
    return this.http.post(this.baseUrl + "new", data);
  }
}
