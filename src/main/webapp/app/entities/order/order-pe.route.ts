import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { OrderPeComponent } from './order-pe.component';
import { OrderPeDetailComponent } from './order-pe-detail.component';
import { OrderPePopupComponent } from './order-pe-dialog.component';
import { OrderPeDeletePopupComponent } from './order-pe-delete-dialog.component';

@Injectable()
export class OrderPeResolvePagingParams implements Resolve<any> {

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

export const orderRoute: Routes = [
    {
        path: 'order-pe',
        component: OrderPeComponent,
        resolve: {
            'pagingParams': OrderPeResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.order.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'order-pe/:id',
        component: OrderPeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.order.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const orderPopupRoute: Routes = [
    {
        path: 'order-pe-new',
        component: OrderPePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.order.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'order-pe/:id/edit',
        component: OrderPePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.order.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'order-pe/:id/delete',
        component: OrderPeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.order.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
