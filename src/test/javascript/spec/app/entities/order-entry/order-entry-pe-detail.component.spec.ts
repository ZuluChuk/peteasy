/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PeteasyTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { OrderEntryPeDetailComponent } from '../../../../../../main/webapp/app/entities/order-entry/order-entry-pe-detail.component';
import { OrderEntryPeService } from '../../../../../../main/webapp/app/entities/order-entry/order-entry-pe.service';
import { OrderEntryPe } from '../../../../../../main/webapp/app/entities/order-entry/order-entry-pe.model';

describe('Component Tests', () => {

    describe('OrderEntryPe Management Detail Component', () => {
        let comp: OrderEntryPeDetailComponent;
        let fixture: ComponentFixture<OrderEntryPeDetailComponent>;
        let service: OrderEntryPeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PeteasyTestModule],
                declarations: [OrderEntryPeDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    OrderEntryPeService,
                    JhiEventManager
                ]
            }).overrideTemplate(OrderEntryPeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OrderEntryPeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OrderEntryPeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new OrderEntryPe(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.orderEntry).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
