import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiConfig } from '../../api-config';
import { Country } from '../../models/country';

@Injectable({
  providedIn: 'root'
})
export class CountriesService {

  private httpClient: HttpClient;

  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient;
  }

  getCountries() {
    return this.httpClient.get(ApiConfig.baseUrl + '/countries');
  }

  createCountry(country: Country) {
    return this.httpClient.post(ApiConfig.baseUrl + '/countries', country);
  }

  deleteCountry(id: string) {
    return this.httpClient.delete(ApiConfig.baseUrl + `/countries/${id}`);
  }
}
