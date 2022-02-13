import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DiyUser} from "../model/user.model";
import {DiyWorkshop} from "../model/workshop.model";

const API_URL = 'http://localhost:8080/api/test/workshop/';

@Injectable({
  providedIn: 'root'
})
export class WorkshopService {
  constructor(private http: HttpClient) { }

  getAll(): Observable<DiyWorkshop[]> {
    return this.http.get<DiyWorkshop[]>(API_URL + "all");
  }
}
