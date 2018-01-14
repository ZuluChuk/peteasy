import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { OrderPe } from './order-pe.model';
import { OrderPePopupService } from './order-pe-popup.service';
import { OrderPeService } from './order-pe.service';
import { AbstractOrderPe, AbstractOrderPeService } from '../abstract-order';
import { CustomerPe, CustomerPeService } from '../customer';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-order-pe-dialog',
    templateUrl: './order-pe-dialog.component.html'
})
export class OrderPeDialogComponent implements OnInit {

    order: OrderPe;
    isSaving: boolean;

    abstractorders: AbstractOrderPe[];

    customers: CustomerPe[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private orderService: OrderPeService,
        private abstractOrderService: AbstractOrderPeService,
        private customerService: CustomerPeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.abstractOrderService
            .query({filter: 'order-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.order.abstractOrderId) {
                    this.abstractorders = res.json;
                } else {
                    this.abstractOrderService
                        .find(this.order.abstractOrderId)
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
        if (this.order.id !== undefined) {
            this.subscribeToSaveResponse(
                this.orderService.update(this.order));
        } else {
            this.subscribeToSaveResponse(
                this.orderService.create(this.order));
        }
    }

    private subscribeToSaveResponse(result: Observable<OrderPe>) {
        result.subscribe((res: OrderPe) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: OrderPe) {
        this.eventManager.broadcast({ name: 'orderListModification', content: 'OK'});
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
    selector: 'jhi-order-pe-popup',
    template: ''
})
export class OrderPePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private orderPopupService: OrderPePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.orderPopupService
                    .open(OrderPeDialogComponent as Component, params['id']);
            } else {
                this.orderPopupService
                    .open(OrderPeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
