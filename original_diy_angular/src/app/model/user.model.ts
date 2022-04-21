import { DiyBadge } from "./badge.model";

export class DiyUser {
    id?: number;
    username?: string;
    firstName?: string;
    lastName?: string;
    phone?: number;
    email?: string;
    role?: any;
    password?: string;
    roles?: any;
    badges?: DiyBadge[];
    badgesSelected?: number[];
}
