import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {Observable} from "rxjs";
import {DiyFooter} from "../model/footer.model";

const API_URL = 'http://localhost:8080/api/test/footer/';

@Injectable({
  providedIn: 'root'
})
export class FooterService {
  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<DiyFooter[]> {
    return this.httpClient.get<DiyFooter[]>(API_URL + "all");
  }
}
