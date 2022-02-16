import { Injectable } from "@angular/core";
import {
  HTTP_INTERCEPTORS,
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from "@angular/common/http";
import { TokenStorageService } from "../service/token-storage.service";
import { catchError, Observable, throwError } from "rxjs";
import { Router } from "@angular/router";

const TOKEN_HEADER_KEY = 'Authorization'

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private token: TokenStorageService, private router: Router) { }

  public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authenticationRequest = req;
    const token = this.token.getToken();

    if (token != null) {
      authenticationRequest = req.clone({
        headers: req.headers.set(TOKEN_HEADER_KEY, token)
      });
    }

    return next.handle(authenticationRequest).pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMsg = '';
        if (error.error instanceof ErrorEvent) {
          console.log('this is client side error');
          errorMsg = `Error: ${error.error.message}`;
        }
        else {
          if (error.status === 404) {
            this.router.navigate(['/error-404']);
          } else if (error.status === 500) {
            this.router.navigate(['/error-500']);
          }
        }
        console.log(errorMsg);
        return throwError(errorMsg);
      })
    )
  }

}

export const authInterceptorProviders = [{
  provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true
}]
