import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CountriesComponent} from './countries/countries.component';
import { CreateCountryComponent } from './countries/create-country/create-country.component';
import {StatesComponent} from './states/states.component';
import { UsersComponent } from './users/users.component';
import { CreateUserComponent } from './users/create-user/create-user.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Coliving'
    },
    children: [
      {
        path: '',
        redirectTo: 'countries'
      },
      {
        path: 'countries',
        component: CountriesComponent,
        data: {
          title: 'Countries'
        }
      },
      {
        path: 'countries/create',
        component: CreateCountryComponent,
        data: {
          title: 'Countries'
        }
      },
      {
        path: 'states',
        component: StatesComponent,
        data: {
          title: 'States'
        }
      },
      {
        path: 'users',
        component: UsersComponent,
        data: {
          title: 'Users'
        }
      },
      {
        path: 'users/create',
        component: CreateUserComponent,
        data: {
          title: 'Users'
        }
      }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ColivingRoutingModule { }
