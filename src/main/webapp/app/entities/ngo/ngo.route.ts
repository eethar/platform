import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { NGOComponent } from './ngo.component';
import { NGODetailComponent } from './ngo-detail.component';
import { NGOPopupComponent } from './ngo-dialog.component';
import { NGODeletePopupComponent } from './ngo-delete-dialog.component';

import { Principal } from '../../shared';

export const NGORoute: Routes = [
  {
    path: 'ngo',
    component: NGOComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'eethar.NGO.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'ngo/:id',
    component: NGODetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'eethar.NGO.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const NGOPopupRoute: Routes = [
  {
    path: 'ngo-new',
    component: NGOPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'eethar.NGO.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'ngo/:id/edit',
    component: NGOPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'eethar.NGO.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'ngo/:id/delete',
    component: NGODeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'eethar.NGO.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
