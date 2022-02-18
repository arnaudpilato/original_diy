import { Role } from "./roles.model";

export class DiyUser {
    id?: number;
    username?: string;
    firstName?: string;
    lastName?: string;
    phone?: number;
    email?: string;
    role?: Role;
    password?: string;
    roles?: any;
}
