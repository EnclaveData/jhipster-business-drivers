import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IControl } from 'app/shared/model/control.model';
import { ControlService } from './control.service';
import { IDriver } from 'app/shared/model/driver.model';
import { DriverService } from 'app/entities/driver';

@Component({
    selector: 'jhi-control-update',
    templateUrl: './control-update.component.html'
})
export class ControlUpdateComponent implements OnInit {
    control: IControl;
    isSaving: boolean;

    drivers: IDriver[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected controlService: ControlService,
        protected driverService: DriverService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ control }) => {
            this.control = control;
        });
        this.driverService.query().subscribe(
            (res: HttpResponse<IDriver[]>) => {
                this.drivers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.control.id !== undefined) {
            this.subscribeToSaveResponse(this.controlService.update(this.control));
        } else {
            this.subscribeToSaveResponse(this.controlService.create(this.control));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IControl>>) {
        result.subscribe((res: HttpResponse<IControl>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDriverById(index: number, item: IDriver) {
        return item.id;
    }
}
