import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ProductPeComponent } from './product-pe.component';
import { ProductPeDetailComponent } from './product-pe-detail.component';
import { ProductPePopupComponent } from './product-pe-dialog.component';
import { ProductPeDeletePopupComponent } from './product-pe-delete-dialog.component';

@Injectable()
export class ProductPeResolvePagingParams implements Resolve<any> {

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

export const productRoute: Routes = [
    {
        path: 'product-pe',
        component: ProductPeComponent,
        resolve: {
            'pagingParams': ProductPeResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.product.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'product-pe/:id',
        component: ProductPeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.product.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const productPopupRoute: Routes = [
    {
        path: 'product-pe-new',
        component: ProductPePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.product.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'product-pe/:id/edit',
        component: ProductPePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.product.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'product-pe/:id/delete',
        component: ProductPeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'peteasyApp.product.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
