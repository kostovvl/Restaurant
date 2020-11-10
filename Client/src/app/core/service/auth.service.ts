import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly register_url = 'http://localhost:8080/waiters/add';
  private readonly login_url = 'http://localhost:8080/waiters/login';

  constructor(private http: HttpClient) { }

  register(form: Object) {
    return this.http.post(this.register_url, form);
  }

  login(form: Object): Observable<string[]> {
    return this.http.post<string[]>(this.login_url, form);
  }

  saveUserInfo(info: string[]) {

  }

}
