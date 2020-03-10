import { Component, OnInit } from '@angular/core';
import { navItems } from '../../_nav';
import { Router } from '@angular/router';
import { UsersService } from '../../services/users/users.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html'
})
export class DefaultLayoutComponent implements OnInit {
  public sidebarMinimized = false;
  public navItems = navItems;
  public router: Router;
  usersService: UsersService;


  currentUser: User;

  constructor(router: Router, usersService: UsersService) {
    this.router = router;
    this.usersService = usersService;
    this.currentUser = new User();
  }

  ngOnInit(): void {
    const authToken = localStorage.getItem('auth_id');
    if (!authToken || !this.checkTokenExpiration()) {
      this.logout();
    } else {
        this.usersService.getCurrentUser().subscribe((resp: User) => {
          this.currentUser = resp;
        });
    }
  }

  toggleMinimize(e) {
    this.sidebarMinimized = e;
  }

  checkTokenExpiration() : Boolean{
    const TWELVE_HOURS : number = 720 * 60 * 3600;
    const issuedAtItem = localStorage.getItem('issued_at');

    if(!issuedAtItem){
      return false;
    }

    const lastValidDate : Date = new Date(parseInt(issuedAtItem + TWELVE_HOURS));
    const now : Date = new Date(Date.now());

    if(lastValidDate > now){
      return false;
    }

    return true;
  }

  logout() {
    // TODO: Logout user
    localStorage.removeItem('auth_id');
    this.router.navigateByUrl('login');
  }
}
