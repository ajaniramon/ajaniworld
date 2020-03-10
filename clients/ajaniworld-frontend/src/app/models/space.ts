import {SpaceImage} from  './space-image';
import { State } from './state';

export class Space {
  id: String;
  address: String;
  city: String;
  zipCode: String;
  state: State;
  starredImage: SpaceImage;
}
