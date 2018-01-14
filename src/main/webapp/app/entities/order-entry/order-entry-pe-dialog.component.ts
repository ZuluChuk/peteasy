import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { OrderEntryPe } from './order-entry-pe.model';
import { OrderEntryPePopupService } from './order-entry-pe-popup.service';
import { OrderEntryPeService } from './order-entry-pe.service';
import { ProductPe, ProductPeService } from '../product';
import { AbstractOrderPe, AbstractOrderPeService } from '../abstract-order';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-order-entry-pe-dialog',
    templateUrl: './order-entry-pe-dialog.component.html'
})
export class OrderEntryPeDialogComponent implements OnInit {

    orderEntry: OrderEntryPe;
    isSaving: boolean;

    products: ProductPe[];

    abstractorders: AbstractOrderPe[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private orderEntryService: OrderEntryPeService,
        private productService: ProductPeService,
        private abstractOrderService: AbstractOrderPeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.productService.query()
            .subscribe((res: ResponseWrapper) => { this.products = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.abstractOrderService.query()
            .subscribe((res: ResponseWrapper) => { this.abstractorders = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.orderEntry.id !== undefined) {
            this.subscribeToSaveResponse(
                this.orderEntryService.update(this.orderEntry));
        } else {
            this.subscribeToSaveResponse(
                this.orderEntryService.create(this.orderEntry));
        }
    }

    private subscribeToSaveResponse(result: Observable<OrderEntryPe>) {
        result.subscribe((res: OrderEntryPe) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: OrderEntryPe) {
        this.eventManager.broadcast({ name: 'orderEntryListModification', content: 'OK'});
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

    trackProductById(index: number, item: ProductPe) {
        return item.id;
    }

    trackAbstractOrderById(index: number, item: AbstractOrderPe) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-order-entry-pe-popup',
    template: ''
})
export class OrderEntryPePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private orderEntryPopupService: OrderEntryPePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.orderEntryPopupService
                    .open(OrderEntryPeDialogComponent as Component, params['id']);
            } else {
                this.orderEntryPopupService
                    .open(OrderEntryPeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
