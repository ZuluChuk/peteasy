import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CountryPeComponent } from './country-pe.component';
import { CountryPeDetailComponent } from './country-pe-detail.component';
import { CountryPePopupComponent } from './country-pe-dialog.component';
import { CountryPeDeletePopupComponent } from './country-pe-delete-dialog.component';

export const countryRoute: Routes = [
    {
        path: 'country-pe',
        component: CountryPeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.country.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'country-pe/:id',
        component: CountryPeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.country.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const countryPopupRoute: Routes = [
    {
        path: 'country-pe-new',
        component: CountryPePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.country.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'country-pe/:id/edit',
        component: CountryPePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.country.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'country-pe/:id/delete',
        component: CountryPeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.country.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
