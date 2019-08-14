import { Injectable } from '@angular/core';
import { Message } from '../classes/message';

@Injectable()
export class UtilityService {

  constructor() { }

  contains(messaages: Array<Message>, id: string) {
    for (const msg of messaages) {
      if (msg.messageId === id) {
        return true;
      }
    }
    return false;
  }

  isUrl(str: string): RegExpMatchArray {
    const expression = /https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?,&//=]*)/;
    const regExp = new RegExp(expression, 'g');
    return str.match(regExp);
  }

  today(): string {
    let now = new Date();
    return `${now.getMonth() + 1}/${now.getDate()}/${now.getFullYear()}`;
  }
}
