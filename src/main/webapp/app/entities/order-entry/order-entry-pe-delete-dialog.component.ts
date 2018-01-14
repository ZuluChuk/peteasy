import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { OrderEntryPe } from './order-entry-pe.model';
import { OrderEntryPePopupService } from './order-entry-pe-popup.service';
import { OrderEntryPeService } from './order-entry-pe.service';

@Component({
    selector: 'jhi-order-entry-pe-delete-dialog',
    templateUrl: './order-entry-pe-delete-dialog.component.html'
})
export class OrderEntryPeDeleteDialogComponent {

    orderEntry: OrderEntryPe;

    constructor(
        private orderEntryService: OrderEntryPeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.orderEntryService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'orderEntryListModification',
                content: 'Deleted an orderEntry'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-order-entry-pe-delete-popup',
    template: ''
})
export class OrderEntryPeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private orderEntryPopupService: OrderEntryPePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.orderEntryPopupService
                .open(OrderEntryPeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
