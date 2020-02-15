import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiConfig } from '../../api-config';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private httpClient: HttpClient;

  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient;
   }

   getUsers() {
     return this.httpClient.get(ApiConfig.baseUrl + '/users');
   }

   getCurrentUser() {
      return this.httpClient.get(ApiConfig.baseUrl + '/users/current');
   }
}
