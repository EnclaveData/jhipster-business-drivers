import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterBusinessDriversDriverModule } from './driver/driver.module';
import { JhipsterBusinessDriversAttributeModule } from './attribute/attribute.module';
import { JhipsterBusinessDriversControlModule } from './control/control.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        JhipsterBusinessDriversDriverModule,
        JhipsterBusinessDriversAttributeModule,
        JhipsterBusinessDriversControlModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterBusinessDriversEntityModule {}
