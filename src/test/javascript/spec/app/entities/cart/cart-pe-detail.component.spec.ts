/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PeteasyTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { CartPeDetailComponent } from '../../../../../../main/webapp/app/entities/cart/cart-pe-detail.component';
import { CartPeService } from '../../../../../../main/webapp/app/entities/cart/cart-pe.service';
import { CartPe } from '../../../../../../main/webapp/app/entities/cart/cart-pe.model';

describe('Component Tests', () => {

    describe('CartPe Management Detail Component', () => {
        let comp: CartPeDetailComponent;
        let fixture: ComponentFixture<CartPeDetailComponent>;
        let service: CartPeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PeteasyTestModule],
                declarations: [CartPeDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    CartPeService,
                    JhiEventManager
                ]
            }).overrideTemplate(CartPeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CartPeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CartPeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new CartPe(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.cart).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
