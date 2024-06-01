import { createSelector } from '@ngrx/store';
import { AppStateInterface } from '../../interfaces/appState.interface';

export const selectFeature = (state: AppStateInterface)=> state.course;

export const courseSelector = createSelector(
    selectFeature, 
    (state) => state.course
);

export const errorSelector = createSelector(
    selectFeature, 
    (state) => state.error
);