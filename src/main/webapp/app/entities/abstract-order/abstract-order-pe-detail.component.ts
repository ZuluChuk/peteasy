import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AbstractOrderPe } from './abstract-order-pe.model';
import { AbstractOrderPeService } from './abstract-order-pe.service';

@Component({
    selector: 'jhi-abstract-order-pe-detail',
    templateUrl: './abstract-order-pe-detail.component.html'
})
export class AbstractOrderPeDetailComponent implements OnInit, OnDestroy {

    abstractOrder: AbstractOrderPe;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private abstractOrderService: AbstractOrderPeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAbstractOrders();
    }

    load(id) {
        this.abstractOrderService.find(id).subscribe((abstractOrder) => {
            this.abstractOrder = abstractOrder;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAbstractOrders() {
        this.eventSubscriber = this.eventManager.subscribe(
            'abstractOrderListModification',
            (response) => this.load(this.abstractOrder.id)
        );
    }
}
