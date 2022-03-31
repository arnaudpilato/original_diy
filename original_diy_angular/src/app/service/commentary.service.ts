import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { DiyWorkshop } from "../model/workshop.model";
import {environment} from "../../environments/environment";
import {DiyComment} from "../model/commentary.model";

@Injectable({
  providedIn: 'root'
})
export class CommentaryService {
  baseUrl = environment.baseUrl + '/test/comment/';

  constructor(private http: HttpClient) { }

  getAll(): Observable<DiyComment[]> {
    return this.http.get<DiyComment[]>(this.baseUrl + "all");
  }

  getAllConfirmed(): Observable<DiyComment[]> {
    return this.http.get<DiyComment[]>(this.baseUrl + "allConfirmed");
  }

  getCommentaryByWorkshop(id: any): Observable<DiyComment[]> {
    return this.http.get<DiyComment[]>(this.baseUrl + "comment-by-workshop/" + id);
  }

  create(data: any): Observable<any> {
    return this.http.post(this.baseUrl + "new", data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(this.baseUrl + "delete/" + id);
  }

  confirmed(id: number): Observable<any> {
    return this.http.get(this.baseUrl + "confirm/" + id);
  }
}
