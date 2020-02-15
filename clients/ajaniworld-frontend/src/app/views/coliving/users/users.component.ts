import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/user';
import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgxSpinnerService } from 'ngx-spinner';
import { UsersService } from '../../../services/users/users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  router: Router;
  users: User[];
  usersService: UsersService;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  modalService: NgbModal;
  spinner: NgxSpinnerService;
  dataTableInitialized: boolean = false;

  userCode: string;

  constructor(router: Router, usersService: UsersService, modalService: NgbModal, spinner: NgxSpinnerService) {
    this.router = router;
    this.usersService = usersService;
    this.modalService = modalService;
    this.spinner = spinner;

  }

  ngOnInit() {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      processing: true
    };

    this.getUsers();
  }

  getUsers() {
    this.spinner.show();

    this.usersService.getUsers().subscribe((resp: User[]) => {
      this.users = resp;

      if (!this.dataTableInitialized) {
        this.dtTrigger.next();
        this.dataTableInitialized = true;
      }

      this.spinner.hide();
    });
  }

  openDeleteModal(content: any, userId: string, userCode: string) {

  }

  deleteUser() {

  }

  createUser() {
    this.router.navigateByUrl('coliving/users/create');
  }
}
