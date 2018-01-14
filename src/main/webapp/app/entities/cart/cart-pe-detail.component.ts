import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { CartPe } from './cart-pe.model';
import { CartPeService } from './cart-pe.service';

@Component({
    selector: 'jhi-cart-pe-detail',
    templateUrl: './cart-pe-detail.component.html'
})
export class CartPeDetailComponent implements OnInit, OnDestroy {

    cart: CartPe;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private cartService: CartPeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCarts();
    }

    load(id) {
        this.cartService.find(id).subscribe((cart) => {
            this.cart = cart;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCarts() {
        this.eventSubscriber = this.eventManager.subscribe(
            'cartListModification',
            (response) => this.load(this.cart.id)
        );
    }
}
