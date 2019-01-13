import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAttribute } from 'app/shared/model/attribute.model';
import { AttributeService } from './attribute.service';
import { IDriver } from 'app/shared/model/driver.model';
import { DriverService } from 'app/entities/driver';

@Component({
    selector: 'jhi-attribute-update',
    templateUrl: './attribute-update.component.html'
})
export class AttributeUpdateComponent implements OnInit {
    attribute: IAttribute;
    isSaving: boolean;

    drivers: IDriver[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected attributeService: AttributeService,
        protected driverService: DriverService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ attribute }) => {
            this.attribute = attribute;
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
        if (this.attribute.id !== undefined) {
            this.subscribeToSaveResponse(this.attributeService.update(this.attribute));
        } else {
            this.subscribeToSaveResponse(this.attributeService.create(this.attribute));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAttribute>>) {
        result.subscribe((res: HttpResponse<IAttribute>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
