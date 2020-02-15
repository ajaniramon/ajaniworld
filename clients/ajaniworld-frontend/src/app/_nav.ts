import { INavData } from '@coreui/angular';

export const navItems: INavData[] = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    icon: 'icon-speedometer',
    // badge: {
    //   variant: 'info',
    //   text: 'NEW'
    // }
  },
  {
    title: true,
    name: 'Coliving'
  },
  {
    name: 'Countries',
    url: '/coliving/countries',
    icon: 'icon-globe'
  },
  {
    name: 'States',
    url: '/coliving/states',
    icon: 'icon-pie-chart'
  },
  {
    name: 'Users',
    url: '/coliving/users',
    icon: 'icon-people'
  }
];
