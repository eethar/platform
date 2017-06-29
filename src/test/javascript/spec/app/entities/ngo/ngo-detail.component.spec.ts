import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { eetharTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { NGODetailComponent } from '../../../../../../main/webapp/app/entities/ngo/ngo-detail.component';
import { NGOService } from '../../../../../../main/webapp/app/entities/ngo/ngo.service';
import { NGO } from '../../../../../../main/webapp/app/entities/ngo/ngo.model';

describe('Component Tests', () => {

    describe('NGO Management Detail Component', () => {
        let comp: NGODetailComponent;
        let fixture: ComponentFixture<NGODetailComponent>;
        let service: NGOService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [eetharTestModule],
                declarations: [NGODetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    NGOService,
                    EventManager
                ]
            }).overrideComponent(NGODetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(NGODetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NGOService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new NGO(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.NGO).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
