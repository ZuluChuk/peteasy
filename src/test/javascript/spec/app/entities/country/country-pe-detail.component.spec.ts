/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PeteasyTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { CountryPeDetailComponent } from '../../../../../../main/webapp/app/entities/country/country-pe-detail.component';
import { CountryPeService } from '../../../../../../main/webapp/app/entities/country/country-pe.service';
import { CountryPe } from '../../../../../../main/webapp/app/entities/country/country-pe.model';

describe('Component Tests', () => {

    describe('CountryPe Management Detail Component', () => {
        let comp: CountryPeDetailComponent;
        let fixture: ComponentFixture<CountryPeDetailComponent>;
        let service: CountryPeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PeteasyTestModule],
                declarations: [CountryPeDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    CountryPeService,
                    JhiEventManager
                ]
            }).overrideTemplate(CountryPeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CountryPeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CountryPeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new CountryPe(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.country).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
