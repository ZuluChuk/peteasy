import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { OrderPe } from './order-pe.model';
import { OrderPeService } from './order-pe.service';

@Component({
    selector: 'jhi-order-pe-detail',
    templateUrl: './order-pe-detail.component.html'
})
export class OrderPeDetailComponent implements OnInit, OnDestroy {

    order: OrderPe;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private orderService: OrderPeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOrders();
    }

    load(id) {
        this.orderService.find(id).subscribe((order) => {
            this.order = order;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInOrders() {
        this.eventSubscriber = this.eventManager.subscribe(
            'orderListModification',
            (response) => this.load(this.order.id)
        );
    }
}
