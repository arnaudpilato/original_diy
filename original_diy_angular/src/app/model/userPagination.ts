import { DiyUser } from "./user.model";

export class UserPagination {
  totalItems?: number;
  totalPages?: number;
  currentPage?: number;
  users: DiyUser[]= [];
}
