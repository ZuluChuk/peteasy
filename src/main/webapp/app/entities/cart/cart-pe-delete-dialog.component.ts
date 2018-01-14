import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CartPe } from './cart-pe.model';
import { CartPePopupService } from './cart-pe-popup.service';
import { CartPeService } from './cart-pe.service';

@Component({
    selector: 'jhi-cart-pe-delete-dialog',
    templateUrl: './cart-pe-delete-dialog.component.html'
})
export class CartPeDeleteDialogComponent {

    cart: CartPe;

    constructor(
        private cartService: CartPeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cartService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'cartListModification',
                content: 'Deleted an cart'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cart-pe-delete-popup',
    template: ''
})
export class CartPeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private cartPopupService: CartPePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.cartPopupService
                .open(CartPeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
