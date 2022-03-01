import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AmazonS3Service {
  baseUrl = environment.baseUrl + '/auth/';

  constructor(private https: HttpClient) { }

  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    const data: FormData = new FormData();

    data.append('file', file);

    const newRequest = new HttpRequest('POST', this.baseUrl + 'uploadFile', data, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.https.request(newRequest);
  }

  deleteFile(file: any) {
    this.https.post<string>(this.baseUrl + 'deleteFile', file).subscribe(
      res => {
        file = res;
      }
    );
  }
}
