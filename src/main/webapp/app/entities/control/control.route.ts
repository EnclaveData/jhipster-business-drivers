import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Control } from 'app/shared/model/control.model';
import { ControlService } from './control.service';
import { ControlComponent } from './control.component';
import { ControlDetailComponent } from './control-detail.component';
import { ControlUpdateComponent } from './control-update.component';
import { ControlDeletePopupComponent } from './control-delete-dialog.component';
import { IControl } from 'app/shared/model/control.model';

@Injectable({ providedIn: 'root' })
export class ControlResolve implements Resolve<IControl> {
    constructor(private service: ControlService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Control> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Control>) => response.ok),
                map((control: HttpResponse<Control>) => control.body)
            );
        }
        return of(new Control());
    }
}

export const controlRoute: Routes = [
    {
        path: 'control',
        component: ControlComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Controls'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'control/:id/view',
        component: ControlDetailComponent,
        resolve: {
            control: ControlResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Controls'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'control/new',
        component: ControlUpdateComponent,
        resolve: {
            control: ControlResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Controls'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'control/:id/edit',
        component: ControlUpdateComponent,
        resolve: {
            control: ControlResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Controls'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const controlPopupRoute: Routes = [
    {
        path: 'control/:id/delete',
        component: ControlDeletePopupComponent,
        resolve: {
            control: ControlResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Controls'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
