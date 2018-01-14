import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AddressPeComponent } from './address-pe.component';
import { AddressPeDetailComponent } from './address-pe-detail.component';
import { AddressPePopupComponent } from './address-pe-dialog.component';
import { AddressPeDeletePopupComponent } from './address-pe-delete-dialog.component';

export const addressRoute: Routes = [
    {
        path: 'address-pe',
        component: AddressPeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.address.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'address-pe/:id',
        component: AddressPeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.address.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const addressPopupRoute: Routes = [
    {
        path: 'address-pe-new',
        component: AddressPePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.address.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'address-pe/:id/edit',
        component: AddressPePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.address.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'address-pe/:id/delete',
        component: AddressPeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.address.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
