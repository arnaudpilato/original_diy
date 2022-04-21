import {DiyUser} from "./user.model";

export class DiyBadge {
  id?: number;
  name?: string;
  picturePath?: string;
  description?: string;
  condition?: string;
  step?: number;
  peoples?: number[];
  users?: DiyUser[];
}
