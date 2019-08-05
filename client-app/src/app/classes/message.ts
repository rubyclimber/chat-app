import { IEntity } from './ientity';

export class Message implements IEntity {
    body: string;
    conversationId?: string;
    createdAt?: Date;
    userId: string;
    id: string;
}
