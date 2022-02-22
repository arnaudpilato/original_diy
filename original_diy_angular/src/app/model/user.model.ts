import { DiyRole } from "./role.model";

export class DiyUser {
    id?: number;
    username?: string;
    firstName?: string;
    lastName?: string;
    phone?: number;
    email?: string;
    role?: DiyRole;
    password?: string;
    roles?: any;
}
