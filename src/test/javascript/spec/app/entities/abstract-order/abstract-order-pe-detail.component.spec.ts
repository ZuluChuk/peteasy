/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PeteasyTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AbstractOrderPeDetailComponent } from '../../../../../../main/webapp/app/entities/abstract-order/abstract-order-pe-detail.component';
import { AbstractOrderPeService } from '../../../../../../main/webapp/app/entities/abstract-order/abstract-order-pe.service';
import { AbstractOrderPe } from '../../../../../../main/webapp/app/entities/abstract-order/abstract-order-pe.model';

describe('Component Tests', () => {

    describe('AbstractOrderPe Management Detail Component', () => {
        let comp: AbstractOrderPeDetailComponent;
        let fixture: ComponentFixture<AbstractOrderPeDetailComponent>;
        let service: AbstractOrderPeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PeteasyTestModule],
                declarations: [AbstractOrderPeDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AbstractOrderPeService,
                    JhiEventManager
                ]
            }).overrideTemplate(AbstractOrderPeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AbstractOrderPeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AbstractOrderPeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AbstractOrderPe(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.abstractOrder).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
