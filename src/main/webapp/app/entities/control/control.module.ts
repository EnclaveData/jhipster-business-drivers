import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterBusinessDriversSharedModule } from 'app/shared';
import {
    ControlComponent,
    ControlDetailComponent,
    ControlUpdateComponent,
    ControlDeletePopupComponent,
    ControlDeleteDialogComponent,
    controlRoute,
    controlPopupRoute
} from './';

const ENTITY_STATES = [...controlRoute, ...controlPopupRoute];

@NgModule({
    imports: [JhipsterBusinessDriversSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ControlComponent,
        ControlDetailComponent,
        ControlUpdateComponent,
        ControlDeleteDialogComponent,
        ControlDeletePopupComponent
    ],
    entryComponents: [ControlComponent, ControlUpdateComponent, ControlDeleteDialogComponent, ControlDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterBusinessDriversControlModule {}
