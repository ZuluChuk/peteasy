import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PeteasySharedModule } from '../../shared';
import {
    CountryPeService,
    CountryPePopupService,
    CountryPeComponent,
    CountryPeDetailComponent,
    CountryPeDialogComponent,
    CountryPePopupComponent,
    CountryPeDeletePopupComponent,
    CountryPeDeleteDialogComponent,
    countryRoute,
    countryPopupRoute,
} from './';

const ENTITY_STATES = [
    ...countryRoute,
    ...countryPopupRoute,
];

@NgModule({
    imports: [
        PeteasySharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CountryPeComponent,
        CountryPeDetailComponent,
        CountryPeDialogComponent,
        CountryPeDeleteDialogComponent,
        CountryPePopupComponent,
        CountryPeDeletePopupComponent,
    ],
    entryComponents: [
        CountryPeComponent,
        CountryPeDialogComponent,
        CountryPePopupComponent,
        CountryPeDeleteDialogComponent,
        CountryPeDeletePopupComponent,
    ],
    providers: [
        CountryPeService,
        CountryPePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PeteasyCountryPeModule {}
