import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserData } from './login/UserPayload';
import { Observable } from 'rxjs';
import { ResponseRecap } from './recaptcha/RecaptchaPayload';

@Injectable({
  providedIn: 'root'
})
export class SocialloginServiceService {

  readonly url:string = 'http://localhost:8080/api/';
  constructor(private http: HttpClient) { }

  Savesresponse(user: UserData): void {
    this.http.post(this.url+'user', user);
  }

  Savesresponsetoken(token: string): Observable<ResponseRecap> {
    return this.http.post<ResponseRecap>(this.url+'recaptcha', token);
  }
}
