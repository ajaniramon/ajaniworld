import { Injectable } from '@angular/core';
import { ApiConfig } from '../../api-config';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LoginResult } from '../../models/login-result';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private token: String;
  private httpClient: HttpClient;
  private router: Router;

  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient;
  }

  login(username: String, password: String) {
    return this.httpClient.post(ApiConfig.baseUrl + '/login', { username: username, password: password });
  }
}
