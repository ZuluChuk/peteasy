import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CartPe } from './cart-pe.model';
import { CartPePopupService } from './cart-pe-popup.service';
import { CartPeService } from './cart-pe.service';
import { AbstractOrderPe, AbstractOrderPeService } from '../abstract-order';
import { CustomerPe, CustomerPeService } from '../customer';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-cart-pe-dialog',
    templateUrl: './cart-pe-dialog.component.html'
})
export class CartPeDialogComponent implements OnInit {

    cart: CartPe;
    isSaving: boolean;

    abstractorders: AbstractOrderPe[];

    customers: CustomerPe[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private cartService: CartPeService,
        private abstractOrderService: AbstractOrderPeService,
        private customerService: CustomerPeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.abstractOrderService
            .query({filter: 'cart-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.cart.abstractOrderId) {
                    this.abstractorders = res.json;
                } else {
                    this.abstractOrderService
                        .find(this.cart.abstractOrderId)
                        .subscribe((subRes: AbstractOrderPe) => {
                            this.abstractorders = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.customerService.query()
            .subscribe((res: ResponseWrapper) => { this.customers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.cart.id !== undefined) {
            this.subscribeToSaveResponse(
                this.cartService.update(this.cart));
        } else {
            this.subscribeToSaveResponse(
                this.cartService.create(this.cart));
        }
    }

    private subscribeToSaveResponse(result: Observable<CartPe>) {
        result.subscribe((res: CartPe) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: CartPe) {
        this.eventManager.broadcast({ name: 'cartListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackAbstractOrderById(index: number, item: AbstractOrderPe) {
        return item.id;
    }

    trackCustomerById(index: number, item: CustomerPe) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-cart-pe-popup',
    template: ''
})
export class CartPePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private cartPopupService: CartPePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.cartPopupService
                    .open(CartPeDialogComponent as Component, params['id']);
            } else {
                this.cartPopupService
                    .open(CartPeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
