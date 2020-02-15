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

    if (!authToken) {
      this.router.navigateByUrl('login');
    } else {
        this.usersService.getCurrentUser().subscribe((resp: User) => {
          this.currentUser = resp;
        });
    }
  }

  toggleMinimize(e) {
    this.sidebarMinimized = e;
  }

  logout() {
    // TODO: Logout user
    this.router.navigateByUrl('login');
    localStorage.removeItem('auth_id');
  }
}
