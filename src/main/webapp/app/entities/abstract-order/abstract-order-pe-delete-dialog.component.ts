import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AbstractOrderPe } from './abstract-order-pe.model';
import { AbstractOrderPePopupService } from './abstract-order-pe-popup.service';
import { AbstractOrderPeService } from './abstract-order-pe.service';

@Component({
    selector: 'jhi-abstract-order-pe-delete-dialog',
    templateUrl: './abstract-order-pe-delete-dialog.component.html'
})
export class AbstractOrderPeDeleteDialogComponent {

    abstractOrder: AbstractOrderPe;

    constructor(
        private abstractOrderService: AbstractOrderPeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.abstractOrderService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'abstractOrderListModification',
                content: 'Deleted an abstractOrder'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-abstract-order-pe-delete-popup',
    template: ''
})
export class AbstractOrderPeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private abstractOrderPopupService: AbstractOrderPePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.abstractOrderPopupService
                .open(AbstractOrderPeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
