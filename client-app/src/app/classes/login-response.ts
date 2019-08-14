import { User } from './user';

export class LoginResponse {
    success: boolean;
    user: User;
    socketUrl: string;
}
