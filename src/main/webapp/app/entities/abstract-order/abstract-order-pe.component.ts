import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { AbstractOrderPe } from './abstract-order-pe.model';
import { AbstractOrderPeService } from './abstract-order-pe.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-abstract-order-pe',
    templateUrl: './abstract-order-pe.component.html'
})
export class AbstractOrderPeComponent implements OnInit, OnDestroy {
abstractOrders: AbstractOrderPe[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private abstractOrderService: AbstractOrderPeService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.abstractOrderService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.abstractOrders = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.abstractOrderService.query().subscribe(
            (res: ResponseWrapper) => {
                this.abstractOrders = res.json;
                this.currentSearch = '';
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInAbstractOrders();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: AbstractOrderPe) {
        return item.id;
    }
    registerChangeInAbstractOrders() {
        this.eventSubscriber = this.eventManager.subscribe('abstractOrderListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
