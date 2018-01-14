import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AbstractOrderPeComponent } from './abstract-order-pe.component';
import { AbstractOrderPeDetailComponent } from './abstract-order-pe-detail.component';
import { AbstractOrderPePopupComponent } from './abstract-order-pe-dialog.component';
import { AbstractOrderPeDeletePopupComponent } from './abstract-order-pe-delete-dialog.component';

export const abstractOrderRoute: Routes = [
    {
        path: 'abstract-order-pe',
        component: AbstractOrderPeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.abstractOrder.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'abstract-order-pe/:id',
        component: AbstractOrderPeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.abstractOrder.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const abstractOrderPopupRoute: Routes = [
    {
        path: 'abstract-order-pe-new',
        component: AbstractOrderPePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.abstractOrder.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'abstract-order-pe/:id/edit',
        component: AbstractOrderPePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.abstractOrder.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'abstract-order-pe/:id/delete',
        component: AbstractOrderPeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.abstractOrder.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
