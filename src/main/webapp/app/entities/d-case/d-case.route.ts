import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { DCaseComponent } from './d-case.component';
import { DCaseDetailComponent } from './d-case-detail.component';
import { DCasePopupComponent } from './d-case-dialog.component';
import { DCaseDeletePopupComponent } from './d-case-delete-dialog.component';

import { Principal } from '../../shared';

export const DCaseRoute: Routes = [
  {
    path: 'd-case',
    component: DCaseComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'eethar.DCase.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'd-case/:id',
    component: DCaseDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'eethar.DCase.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const DCasePopupRoute: Routes = [
  {
    path: 'd-case-new',
    component: DCasePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'eethar.DCase.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'd-case/:id/edit',
    component: DCasePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'eethar.DCase.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'd-case/:id/delete',
    component: DCaseDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'eethar.DCase.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
