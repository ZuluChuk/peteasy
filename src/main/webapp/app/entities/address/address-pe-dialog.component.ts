import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AddressPe } from './address-pe.model';
import { AddressPePopupService } from './address-pe-popup.service';
import { AddressPeService } from './address-pe.service';
import { CountryPe, CountryPeService } from '../country';
import { CustomerPe, CustomerPeService } from '../customer';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-address-pe-dialog',
    templateUrl: './address-pe-dialog.component.html'
})
export class AddressPeDialogComponent implements OnInit {

    address: AddressPe;
    isSaving: boolean;

    countries: CountryPe[];

    customers: CustomerPe[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private addressService: AddressPeService,
        private countryService: CountryPeService,
        private customerService: CustomerPeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.countryService.query()
            .subscribe((res: ResponseWrapper) => { this.countries = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.customerService.query()
            .subscribe((res: ResponseWrapper) => { this.customers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.address.id !== undefined) {
            this.subscribeToSaveResponse(
                this.addressService.update(this.address));
        } else {
            this.subscribeToSaveResponse(
                this.addressService.create(this.address));
        }
    }

    private subscribeToSaveResponse(result: Observable<AddressPe>) {
        result.subscribe((res: AddressPe) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: AddressPe) {
        this.eventManager.broadcast({ name: 'addressListModification', content: 'OK'});
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

    trackCountryById(index: number, item: CountryPe) {
        return item.id;
    }

    trackCustomerById(index: number, item: CustomerPe) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-address-pe-popup',
    template: ''
})
export class AddressPePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private addressPopupService: AddressPePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.addressPopupService
                    .open(AddressPeDialogComponent as Component, params['id']);
            } else {
                this.addressPopupService
                    .open(AddressPeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
