import { Component, OnInit } from '@angular/core';
import { SpacesService } from '../../../services/spaces/spaces.service';

@Component({
  selector: 'app-spaces',
  templateUrl: './spaces.component.html',
  styleUrls: ['./spaces.component.css']
})
export class SpacesComponent implements OnInit {

  private spacesService: SpacesService;

  constructor(spacesService: SpacesService) {
    this.spacesService = spacesService;
   }

  ngOnInit() {
  }

}
