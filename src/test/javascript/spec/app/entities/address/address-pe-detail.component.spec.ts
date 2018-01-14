/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PeteasyTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AddressPeDetailComponent } from '../../../../../../main/webapp/app/entities/address/address-pe-detail.component';
import { AddressPeService } from '../../../../../../main/webapp/app/entities/address/address-pe.service';
import { AddressPe } from '../../../../../../main/webapp/app/entities/address/address-pe.model';

describe('Component Tests', () => {

    describe('AddressPe Management Detail Component', () => {
        let comp: AddressPeDetailComponent;
        let fixture: ComponentFixture<AddressPeDetailComponent>;
        let service: AddressPeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PeteasyTestModule],
                declarations: [AddressPeDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AddressPeService,
                    JhiEventManager
                ]
            }).overrideTemplate(AddressPeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AddressPeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AddressPeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AddressPe(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.address).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
