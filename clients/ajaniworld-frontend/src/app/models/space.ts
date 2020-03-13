import {SpaceImage} from  './space-image';
import { State } from './state';

export class Space {
  id: string;
  name: string;
  address: string;
  city: string;
  zipCode: string;
  state: string;
  starredImage: SpaceImage;
}
