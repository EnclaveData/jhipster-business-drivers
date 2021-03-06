import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Attribute } from 'app/shared/model/attribute.model';
import { AttributeService } from './attribute.service';
import { AttributeComponent } from './attribute.component';
import { AttributeDetailComponent } from './attribute-detail.component';
import { AttributeUpdateComponent } from './attribute-update.component';
import { AttributeDeletePopupComponent } from './attribute-delete-dialog.component';
import { IAttribute } from 'app/shared/model/attribute.model';

@Injectable({ providedIn: 'root' })
export class AttributeResolve implements Resolve<IAttribute> {
    constructor(private service: AttributeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Attribute> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Attribute>) => response.ok),
                map((attribute: HttpResponse<Attribute>) => attribute.body)
            );
        }
        return of(new Attribute());
    }
}

export const attributeRoute: Routes = [
    {
        path: 'attribute',
        component: AttributeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Attributes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attribute/:id/view',
        component: AttributeDetailComponent,
        resolve: {
            attribute: AttributeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attributes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attribute/new',
        component: AttributeUpdateComponent,
        resolve: {
            attribute: AttributeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attributes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attribute/:id/edit',
        component: AttributeUpdateComponent,
        resolve: {
            attribute: AttributeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attributes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const attributePopupRoute: Routes = [
    {
        path: 'attribute/:id/delete',
        component: AttributeDeletePopupComponent,
        resolve: {
            attribute: AttributeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attributes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
