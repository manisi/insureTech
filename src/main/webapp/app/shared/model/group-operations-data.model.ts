export const enum EntityEnumName {
    CARGROUP = 'CARGROUP',
    CARKIND = 'CARKIND',
    CARSYSTEM = 'CARSYSTEM'
}

export const enum EstateFileItem {
    UPLOADED = 'UPLOADED',
    CONFIRMED = 'CONFIRMED',
    APPLIED = 'APPLIED'
}

export interface IGroupOperationsData {
    id?: number;
    entityName?: EntityEnumName;
    acting?: boolean;
    estate?: EstateFileItem;
    uploadfileContentType?: string;
    uploadfile?: any;
}

export class GroupOperationsData implements IGroupOperationsData {
    constructor(
        public id?: number,
        public entityName?: EntityEnumName,
        public acting?: boolean,
        public estate?: EstateFileItem,
        public uploadfileContentType?: string,
        public uploadfile?: any
    ) {
        this.acting = this.acting || false;
    }
}
