/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PeteasyTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ProductPeDetailComponent } from '../../../../../../main/webapp/app/entities/product/product-pe-detail.component';
import { ProductPeService } from '../../../../../../main/webapp/app/entities/product/product-pe.service';
import { ProductPe } from '../../../../../../main/webapp/app/entities/product/product-pe.model';

describe('Component Tests', () => {

    describe('ProductPe Management Detail Component', () => {
        let comp: ProductPeDetailComponent;
        let fixture: ComponentFixture<ProductPeDetailComponent>;
        let service: ProductPeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PeteasyTestModule],
                declarations: [ProductPeDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ProductPeService,
                    JhiEventManager
                ]
            }).overrideTemplate(ProductPeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProductPeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductPeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ProductPe(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.product).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
