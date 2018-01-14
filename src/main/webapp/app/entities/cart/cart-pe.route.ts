import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CartPeComponent } from './cart-pe.component';
import { CartPeDetailComponent } from './cart-pe-detail.component';
import { CartPePopupComponent } from './cart-pe-dialog.component';
import { CartPeDeletePopupComponent } from './cart-pe-delete-dialog.component';

export const cartRoute: Routes = [
    {
        path: 'cart-pe',
        component: CartPeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.cart.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'cart-pe/:id',
        component: CartPeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.cart.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cartPopupRoute: Routes = [
    {
        path: 'cart-pe-new',
        component: CartPePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.cart.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'cart-pe/:id/edit',
        component: CartPePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.cart.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'cart-pe/:id/delete',
        component: CartPeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.cart.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
