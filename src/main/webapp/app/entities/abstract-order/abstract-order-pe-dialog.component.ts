import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AbstractOrderPe } from './abstract-order-pe.model';
import { AbstractOrderPePopupService } from './abstract-order-pe-popup.service';
import { AbstractOrderPeService } from './abstract-order-pe.service';
import { AddressPe, AddressPeService } from '../address';
import { OrderPe, OrderPeService } from '../order';
import { CartPe, CartPeService } from '../cart';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-abstract-order-pe-dialog',
    templateUrl: './abstract-order-pe-dialog.component.html'
})
export class AbstractOrderPeDialogComponent implements OnInit {

    abstractOrder: AbstractOrderPe;
    isSaving: boolean;

    shippingaddresses: AddressPe[];

    billingaddresses: AddressPe[];

    orders: OrderPe[];

    carts: CartPe[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private abstractOrderService: AbstractOrderPeService,
        private addressService: AddressPeService,
        private orderService: OrderPeService,
        private cartService: CartPeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.addressService
            .query({filter: 'abstractorder-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.abstractOrder.shippingAddressId) {
                    this.shippingaddresses = res.json;
                } else {
                    this.addressService
                        .find(this.abstractOrder.shippingAddressId)
                        .subscribe((subRes: AddressPe) => {
                            this.shippingaddresses = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.addressService
            .query({filter: 'abstractorder-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.abstractOrder.billingAddressId) {
                    this.billingaddresses = res.json;
                } else {
                    this.addressService
                        .find(this.abstractOrder.billingAddressId)
                        .subscribe((subRes: AddressPe) => {
                            this.billingaddresses = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.orderService.query()
            .subscribe((res: ResponseWrapper) => { this.orders = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.cartService.query()
            .subscribe((res: ResponseWrapper) => { this.carts = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.abstractOrder.id !== undefined) {
            this.subscribeToSaveResponse(
                this.abstractOrderService.update(this.abstractOrder));
        } else {
            this.subscribeToSaveResponse(
                this.abstractOrderService.create(this.abstractOrder));
        }
    }

    private subscribeToSaveResponse(result: Observable<AbstractOrderPe>) {
        result.subscribe((res: AbstractOrderPe) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: AbstractOrderPe) {
        this.eventManager.broadcast({ name: 'abstractOrderListModification', content: 'OK'});
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

    trackAddressById(index: number, item: AddressPe) {
        return item.id;
    }

    trackOrderById(index: number, item: OrderPe) {
        return item.id;
    }

    trackCartById(index: number, item: CartPe) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-abstract-order-pe-popup',
    template: ''
})
export class AbstractOrderPePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private abstractOrderPopupService: AbstractOrderPePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.abstractOrderPopupService
                    .open(AbstractOrderPeDialogComponent as Component, params['id']);
            } else {
                this.abstractOrderPopupService
                    .open(AbstractOrderPeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
