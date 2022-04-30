import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {category} from "../model/category.model";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  baseUrlWorkshops = environment.baseUrl + '/test/workshop/';

  constructor(private http: HttpClient) { }

  getAll(): Observable<category[]> {
    return this.http.get<category[]>(this.baseUrlWorkshops + "all/category");
  }
}
