import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { ColivingRoutingModule } from './coliving-routing.module';
import { CountriesComponent } from './countries/countries.component';
import { StatesComponent } from './states/states.component';
import { DataTablesModule } from 'angular-datatables';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CreateCountryComponent } from './countries/create-country/create-country.component';
import { UsersComponent } from './users/users.component';
import { NgxSpinnerModule } from 'ngx-spinner';
import { LightboxModule } from 'ngx-lightbox';
import { CreateUserComponent } from './users/create-user/create-user.component';
import { SpacesComponent } from './spaces/spaces.component';

@NgModule({
  declarations: [CountriesComponent, StatesComponent, CreateCountryComponent, UsersComponent, CreateUserComponent, SpacesComponent],
  imports: [
    CommonModule,
    DataTablesModule,
    FormsModule,
    ReactiveFormsModule,
    LightboxModule,
    ColivingRoutingModule,
    NgbModule,
    NgxSpinnerModule
  ]
})
export class ColivingModule { }
