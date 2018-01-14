import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { OrderEntryPe } from './order-entry-pe.model';
import { OrderEntryPeService } from './order-entry-pe.service';

@Component({
    selector: 'jhi-order-entry-pe-detail',
    templateUrl: './order-entry-pe-detail.component.html'
})
export class OrderEntryPeDetailComponent implements OnInit, OnDestroy {

    orderEntry: OrderEntryPe;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private orderEntryService: OrderEntryPeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOrderEntries();
    }

    load(id) {
        this.orderEntryService.find(id).subscribe((orderEntry) => {
            this.orderEntry = orderEntry;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInOrderEntries() {
        this.eventSubscriber = this.eventManager.subscribe(
            'orderEntryListModification',
            (response) => this.load(this.orderEntry.id)
        );
    }
}
