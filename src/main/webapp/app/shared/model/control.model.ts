import { IDriver } from 'app/shared/model//driver.model';

export interface IControl {
    id?: number;
    name?: string;
    drivers?: IDriver[];
}

export class Control implements IControl {
    constructor(public id?: number, public name?: string, public drivers?: IDriver[]) {}
}
