import { IAttribute } from 'app/shared/model//attribute.model';
import { IControl } from 'app/shared/model//control.model';

export const enum Rating {
    Low = 'Low',
    Medium = 'Medium',
    High = 'High'
}

export interface IDriver {
    id?: number;
    name?: string;
    firstRating?: Rating;
    secondRating?: Rating;
    attributes?: IAttribute[];
    controls?: IControl[];
}

export class Driver implements IDriver {
    constructor(
        public id?: number,
        public name?: string,
        public firstRating?: Rating,
        public secondRating?: Rating,
        public attributes?: IAttribute[],
        public controls?: IControl[]
    ) {}
}
