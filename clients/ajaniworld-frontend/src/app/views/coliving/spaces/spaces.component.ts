import { Component, OnInit } from '@angular/core';
import { SpacesService } from '../../../services/spaces/spaces.service';
import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgxSpinnerService } from 'ngx-spinner';
import { Space } from '../../../models/space';
import { Router } from '@angular/router';
import {Lightbox, IAlbum} from 'ngx-lightbox';

@Component({
  selector: 'app-spaces',
  templateUrl: './spaces.component.html',
  styleUrls: ['./spaces.component.css']
})
export class SpacesComponent implements OnInit {

  private thumbnails: Array<IAlbum> = [];
  private spacesService: SpacesService;
  private router: Router;

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  modalService: NgbModal;

  spaces: Space[];
  spinner: NgxSpinnerService;

  dataTableInitialized: boolean = false;

  constructor(spacesService: SpacesService ,router: Router, modalService: NgbModal, spinner: NgxSpinnerService, private _lightbox: Lightbox) {
    this.spacesService = spacesService;
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

    this.getSpaces();
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  getSpaces(){
    this.spinner.show();
    this.spacesService.getSpaces().subscribe((resp: Space[]) => {
      this.spaces = resp;

      if (!this.dataTableInitialized) {
        this.dtTrigger.next();
        this.dataTableInitialized = true;
      }

      this.spinner.hide();

      for(let i = 0; i < this.spaces.length; i++){
        if(this.spaces[i].starredImage){
          this.thumbnails.push({
            src: this.spaces[i].starredImage.image,
            thumb: this.spaces[i].starredImage.image,
            caption: this.spaces[i].name
          });
        }
      }
    });
  }

  open(index: number): void {
    // open lightbox
    this._lightbox.open(this.thumbnails, index);
  }

}
