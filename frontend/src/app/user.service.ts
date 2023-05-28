import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from './user.model';
import { tap } from 'rxjs';

const USERS_PATH = 'http://localhost:8080/users'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  createUser(user: User & { password: string }) {
    return this.httpClient.post(USERS_PATH, user)
      .pipe(tap())
  }

  listUsers() {
    return this.httpClient.get(USERS_PATH)
      .pipe(tap())
  }

}
