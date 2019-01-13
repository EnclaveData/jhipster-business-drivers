import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDriver } from 'app/shared/model/driver.model';
import { DriverService } from './driver.service';
import { IAttribute } from 'app/shared/model/attribute.model';
import { AttributeService } from 'app/entities/attribute';
import { IControl } from 'app/shared/model/control.model';
import { ControlService } from 'app/entities/control';

@Component({
    selector: 'jhi-driver-update',
    templateUrl: './driver-update.component.html'
})
export class DriverUpdateComponent implements OnInit {
    driver: IDriver;
    isSaving: boolean;

    attributes: IAttribute[];

    controls: IControl[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected driverService: DriverService,
        protected attributeService: AttributeService,
        protected controlService: ControlService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ driver }) => {
            this.driver = driver;
        });
        this.attributeService.query().subscribe(
            (res: HttpResponse<IAttribute[]>) => {
                this.attributes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.controlService.query().subscribe(
            (res: HttpResponse<IControl[]>) => {
                this.controls = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.driver.id !== undefined) {
            this.subscribeToSaveResponse(this.driverService.update(this.driver));
        } else {
            this.subscribeToSaveResponse(this.driverService.create(this.driver));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDriver>>) {
        result.subscribe((res: HttpResponse<IDriver>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackAttributeById(index: number, item: IAttribute) {
        return item.id;
    }

    trackControlById(index: number, item: IControl) {
        return item.id;
    }
}
