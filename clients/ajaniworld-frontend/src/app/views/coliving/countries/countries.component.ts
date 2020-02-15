import { Component, OnInit, OnDestroy } from '@angular/core';
import { CountriesService } from '../../../services/countries/countries.service';
import { Country } from '../../../models/country';
import { Subject } from 'rxjs';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-countries',
  templateUrl: './countries.component.html',
  styleUrls: ['./countries.component.css']
})
export class CountriesComponent implements OnInit, OnDestroy {

  router: Router;

  countriesService: CountriesService;
  countries: Country[];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  modalService: NgbModal;
  spinner: NgxSpinnerService;
  countryId: string;
  countryName: string;
  dataTableInitialized: boolean = false;

  constructor(countriesService: CountriesService, router: Router, modalService: NgbModal, spinner: NgxSpinnerService) {
    this.countriesService = countriesService;
    this.router = router;
    this.modalService = modalService;
    this.spinner = spinner;
  }

  ngOnInit() {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      processing: true
    };

    this.getCountries();
  }

  getCountries() {
    this.spinner.show();
    this.countriesService.getCountries().subscribe((resp: Country[]) => {
      this.countries = resp;

      if (!this.dataTableInitialized) {
        this.dtTrigger.next();
        this.dataTableInitialized = true;
      }

      this.spinner.hide();
    });
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  createCountry() {
    this.router.navigateByUrl('coliving/countries/create');
  }

  openDeleteModal(content, countryId, countryName) {
    this.countryId = countryId;
    this.countryName = countryName;

    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
    }, (reason) => {
    });
  }

  deleteCountry() {
    this.spinner.show();
    this.countriesService.deleteCountry(this.countryId).subscribe((resp: any) => {
      this.modalService.dismissAll();
      this.spinner.hide();
      this.getCountries();
    });
  }
}
