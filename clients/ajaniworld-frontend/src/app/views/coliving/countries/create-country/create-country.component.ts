import { Component, OnInit } from '@angular/core';
import { CountriesService } from '../../../../services/countries/countries.service';
import { Router } from '@angular/router';
import { Country } from '../../../../models/country';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-create-country',
  templateUrl: './create-country.component.html',
  styleUrls: ['./create-country.component.css']
})
export class CreateCountryComponent implements OnInit {
  formBuilder: FormBuilder;
  form: FormGroup;
  countriesService: CountriesService;
  router: Router;
  country: Country;
  spinner: NgxSpinnerService;

  constructor(countriesService: CountriesService, router: Router, formBuilder: FormBuilder, spinner: NgxSpinnerService) {
    this.countriesService = countriesService;
    this.router = router;
    this.country = new Country();
    this.formBuilder = formBuilder;
    this.spinner = spinner;

    this.form = this.formBuilder.group({
      name: new FormControl('', [Validators.required]),
      file: new FormControl('', [Validators.required])
    });
  }

  ngOnInit() {
  }

  back() {
    this.router.navigateByUrl('coliving/countries');
  }

  createCountry() {
    this.spinner.show();

    this.country.name = this.form.get('name').value;

    this.countriesService.createCountry(this.country).subscribe((resp: any) => {
      this.spinner.hide();
      this.router.navigateByUrl('coliving/countries');
    });
  }

  readFile(inputValue: any) {
    const file: File = inputValue.target.files[0];
    const myReader: FileReader = new FileReader();

    myReader.onloadend = (e) => {
      this.country.flag = myReader.result.toString().split(',')[1];
    };

    myReader.readAsDataURL(file);
  }
}
