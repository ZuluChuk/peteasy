import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { OrderEntryPeComponent } from './order-entry-pe.component';
import { OrderEntryPeDetailComponent } from './order-entry-pe-detail.component';
import { OrderEntryPePopupComponent } from './order-entry-pe-dialog.component';
import { OrderEntryPeDeletePopupComponent } from './order-entry-pe-delete-dialog.component';

@Injectable()
export class OrderEntryPeResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const orderEntryRoute: Routes = [
    {
        path: 'order-entry-pe',
        component: OrderEntryPeComponent,
        resolve: {
            'pagingParams': OrderEntryPeResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.orderEntry.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'order-entry-pe/:id',
        component: OrderEntryPeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.orderEntry.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const orderEntryPopupRoute: Routes = [
    {
        path: 'order-entry-pe-new',
        component: OrderEntryPePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.orderEntry.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'order-entry-pe/:id/edit',
        component: OrderEntryPePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.orderEntry.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'order-entry-pe/:id/delete',
        component: OrderEntryPeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.orderEntry.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
