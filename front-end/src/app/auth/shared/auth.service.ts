import { Injectable } from '@angular/core';
import { HttpClient }  from '@angular/common/http';
import { SignupRequestPayload } from '../signup/signup-request.payload';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { LoginRequestPayload } from '../login/login-request.payload';
import { LocalStorageService } from 'ngx-webstorage';
import { LoginResponsePayload } from '../login/login-response.payload';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor( private htttpClient: HttpClient, private localStorage: LocalStorageService) {
   }
  
  signup(signupRequestPayload: SignupRequestPayload):Observable<any>{
    return this.htttpClient.post('http://localhost:8080/api/auth/signup', signupRequestPayload,{responseType: 'text'});
  }

  login(loginRequestPayload:LoginRequestPayload):Observable<any>{
    return this.htttpClient.post<LoginResponsePayload>('http://localhost:8080/api/auth/login', loginRequestPayload).pipe(map(data => {
      this.localStorage.store('authenticationToken', data.authenticationToken);
      this.localStorage.store('refreshToken', data.refreshToken);
      this.localStorage.store('expiresAt', data.expiresAt);
      this.localStorage.store('username', data.username);
    }));
  }
}
