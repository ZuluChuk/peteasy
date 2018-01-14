/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PeteasyTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { OrderPeDetailComponent } from '../../../../../../main/webapp/app/entities/order/order-pe-detail.component';
import { OrderPeService } from '../../../../../../main/webapp/app/entities/order/order-pe.service';
import { OrderPe } from '../../../../../../main/webapp/app/entities/order/order-pe.model';

describe('Component Tests', () => {

    describe('OrderPe Management Detail Component', () => {
        let comp: OrderPeDetailComponent;
        let fixture: ComponentFixture<OrderPeDetailComponent>;
        let service: OrderPeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PeteasyTestModule],
                declarations: [OrderPeDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    OrderPeService,
                    JhiEventManager
                ]
            }).overrideTemplate(OrderPeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OrderPeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OrderPeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new OrderPe(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.order).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
