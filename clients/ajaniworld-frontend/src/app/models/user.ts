import { Role } from './role';

export class User {
  id: string;
  code: string;
  name: string;
  surnames: string;
  avatar: string;
  email: string;
  phone: string;
  roles: Role[];
}
