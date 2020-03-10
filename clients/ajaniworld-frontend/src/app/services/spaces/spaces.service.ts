import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiConfig } from '../../api-config';

@Injectable({
  providedIn: 'root'
})
export class SpacesService {
  private httpClient: HttpClient;

  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient;
   }

   getSpaces(){
     this.httpClient.get(ApiConfig.baseUrl + '/spaces');
   }
}
