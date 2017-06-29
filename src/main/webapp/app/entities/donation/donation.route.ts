import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { DonationComponent } from './donation.component';
import { DonationDetailComponent } from './donation-detail.component';
import { DonationPopupComponent } from './donation-dialog.component';
import { DonationDeletePopupComponent } from './donation-delete-dialog.component';

import { Principal } from '../../shared';

export const donationRoute: Routes = [
  {
    path: 'donation',
    component: DonationComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'eethar.donation.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'donation/:id',
    component: DonationDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'eethar.donation.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const donationPopupRoute: Routes = [
  {
    path: 'donation-new',
    component: DonationPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'eethar.donation.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'donation/:id/edit',
    component: DonationPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'eethar.donation.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'donation/:id/delete',
    component: DonationDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'eethar.donation.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
