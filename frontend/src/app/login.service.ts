import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs';

const LOGIN_PATH = 'http://localhost:8080/login'

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) { }

  login(email: string, password: string) {
    return this.httpClient.post(LOGIN_PATH, { email, password })
      .pipe(tap());
  }
}
