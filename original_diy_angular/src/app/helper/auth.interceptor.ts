import { Injectable } from "@angular/core";
import { HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { TokenStorageService } from "../service/token-storage.service";
import { Observable } from "rxjs";

const TOKEN_HEADER_KEY = 'Authorization'

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private token: TokenStorageService) { }

  public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authenticationRequest = req;
    const token = this.token.getToken();

    if (token != null) {
      authenticationRequest = req.clone({
        headers: req.headers.set(TOKEN_HEADER_KEY, token)
      });
    }

    return next.handle(authenticationRequest);
  }
}

export const authInterceptorProviders = [{
  provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true
}]
