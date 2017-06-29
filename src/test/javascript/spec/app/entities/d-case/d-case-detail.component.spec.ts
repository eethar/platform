import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { eetharTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { DCaseDetailComponent } from '../../../../../../main/webapp/app/entities/d-case/d-case-detail.component';
import { DCaseService } from '../../../../../../main/webapp/app/entities/d-case/d-case.service';
import { DCase } from '../../../../../../main/webapp/app/entities/d-case/d-case.model';

describe('Component Tests', () => {

    describe('DCase Management Detail Component', () => {
        let comp: DCaseDetailComponent;
        let fixture: ComponentFixture<DCaseDetailComponent>;
        let service: DCaseService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [eetharTestModule],
                declarations: [DCaseDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    DCaseService,
                    EventManager
                ]
            }).overrideComponent(DCaseDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DCaseDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DCaseService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new DCase(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.DCase).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
