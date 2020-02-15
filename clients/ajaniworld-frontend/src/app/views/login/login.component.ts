import { Component } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { LoginResult } from '../../models/login-result';
import { Router } from '@angular/router';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html'
})
export class LoginComponent {

  authService: AuthService;
  router: Router;
  spinner: NgxSpinnerService;
  showErrorMessage: boolean;
  username: string;
  password: string;

  constructor(authService: AuthService, router: Router, spinner: NgxSpinnerService) {
    this.authService = authService;
    this.router = router;
    this.spinner = spinner;
  }

  login() {
    this.spinner.show();
    this.authService.login(this.username, this.password).subscribe((resp: LoginResult) => {
      this.spinner.hide();
      if (resp.success && resp.admin) {
          localStorage.setItem('auth_id', resp.token);
          this.router.navigateByUrl('dashboard');
      } else {
          this.showErrorMessage = true;
      }
    });
  }
}
