/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PeteasyTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { CustomerPeDetailComponent } from '../../../../../../main/webapp/app/entities/customer/customer-pe-detail.component';
import { CustomerPeService } from '../../../../../../main/webapp/app/entities/customer/customer-pe.service';
import { CustomerPe } from '../../../../../../main/webapp/app/entities/customer/customer-pe.model';

describe('Component Tests', () => {

    describe('CustomerPe Management Detail Component', () => {
        let comp: CustomerPeDetailComponent;
        let fixture: ComponentFixture<CustomerPeDetailComponent>;
        let service: CustomerPeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PeteasyTestModule],
                declarations: [CustomerPeDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    CustomerPeService,
                    JhiEventManager
                ]
            }).overrideTemplate(CustomerPeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CustomerPeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CustomerPeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new CustomerPe(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.customer).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
