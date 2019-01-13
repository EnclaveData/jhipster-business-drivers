import { NgModule } from '@angular/core';

import { JhipsterBusinessDriversSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [JhipsterBusinessDriversSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [JhipsterBusinessDriversSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class JhipsterBusinessDriversSharedCommonModule {}
