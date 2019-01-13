import { IDriver } from 'app/shared/model//driver.model';

export interface IAttribute {
    id?: number;
    name?: string;
    drivers?: IDriver[];
}

export class Attribute implements IAttribute {
    constructor(public id?: number, public name?: string, public drivers?: IDriver[]) {}
}
