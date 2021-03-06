
import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Message } from '../classes/message';
import { UtilityService } from '../services/utility.service';
import { DataService } from '../services/data.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],

})
export class ChatComponent implements OnInit, OnDestroy {
  userId: string;
  messages: Message[];
  message: string;
  postingMessage: boolean;
  notifyInterval: number;
  pageTitle: string;
  notifyTitle: string;
  pageNumber: number;
  currentScrollHeight: number;
  stompClient;
  serverUrl: String;

  constructor(private utilitySvc: UtilityService, private dataSvc: DataService) {
    this.pageTitle = 'Oh Gnarly';
    this.notifyTitle = 'New Message';
    this.messages = [];
    this.message = '';
    this.notifyInterval = 0;
    this.pageNumber = 0;
    this.userId = this.dataSvc.getUserId();
    this.serverUrl = this.dataSvc.getSocketUrl();

    window.onblur = (() => {
      const button = document.getElementById('sendButton');
      if (button) {
        button.focus();
      }
    }).bind(this);
  }

  ngOnInit() {
    setTimeout(this.getMessages.bind(this), 1, true);

    setTimeout(this.setSocketConnection.bind(this), 1)
  }

  setSocketConnection() {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    this.stompClient.debug = () => {};
    this.stompClient.connect({}, this.connectToSocket.bind(this));
  }

  connectToSocket(frame) {
    this.stompClient.subscribe("/chat-message", this.loadMessage.bind(this));
  }

  ngOnDestroy() {
    const messageField = document.getElementById('message-field');
    if (messageField) {
      messageField.onfocus = undefined;
    }
  }

  getMessages(scrollToBottom: boolean = true): void {
    let today = this.utilitySvc.today();
    this.currentScrollHeight = document.getElementById('chat-window').scrollHeight || 0;
    this.dataSvc.getMessages(this.pageNumber).subscribe(msgs => {
      this.processMessages(msgs);

      let scrollFunction = scrollToBottom ? this.scrollToBottom : this.maintainScollPosition;
      setTimeout(scrollFunction.bind(this), 1);
    });
  }

  submitOnEnter(event: KeyboardEvent): boolean {
    if (event.which === 13 && !event.shiftKey) {
      this.submitMessageSocket();
      return false;
    }

    return true;
  }

  submitMessageSocket(): void {
    if (this.message.trim().length === 0) {
      this.focusText();
      return;
    }

    const message = {
      body: this.message,
      userId: this.userId
    };

    this.stompClient.send('/app/server-message', {}, JSON.stringify(message));
    this.message = '';
  }

  focusText(): void {
    const messageField = document.getElementById('message-field');
    if (messageField) {
      messageField.focus();
    }
  }

  startToggle(): void {
      document.title = this.notifyTitle;

      this.notifyInterval = window.setInterval(() => {
        document.title = document.title === this.notifyTitle ? this.pageTitle : this.notifyTitle;
      }, 1000);
  }

  notifyUser(): void {
    const audio = new Audio();
    audio.src = '../../assets/sms-alert-1-daniel_simon.mp3';
    audio.load();
    audio.play();
  }

  stopToggle(): void {
    window.clearInterval(this.notifyInterval);
    this.notifyInterval = 0;
    document.title = this.pageTitle;
    window.onfocus = undefined;
    const messageField = document.getElementById('message-field');
    if (messageField) {
      messageField.onfocus = undefined;
    }
  }

  processMessages(messages: Message[]) {
    for (const msg of messages) {
      msg.body = this.processMessageBody(msg.body);
    }

    this.messages = messages.concat(this.messages);
  }

  processMessageBody(messageBody: string) {
    messageBody = messageBody.replace(/\n/g, '<br />');
    const matches = this.utilitySvc.isUrl(messageBody);
    if (matches) {
      for (const match of matches) {
        messageBody = messageBody.replace(match, `<a class="gnarly-anchor" href="${match}" target="_blank">${match}</a>`);
      }
    }
    return messageBody;
  }

  loadMessage(data) {
    const message = JSON.parse(data.body) as Message;
    message.body = this.processMessageBody(message.body);
    this.messages.push(message);

    const messageInput = document.getElementById('message-field');
    if (messageInput
      && messageInput !== document.activeElement
      && this.notifyInterval === 0
      && this.message.length === 0
      && message.userId !== this.userId) {
      if (message.userId !== this.userId) {
        this.startToggle();
        window.onfocus = this.focusText.bind(this);
        messageInput.onfocus = this.stopToggle.bind(this);
      }
    }

    setTimeout(this.scrollToBottom.bind(this), 1);
  }

  onWindowScroll() {
    const chatWindow = document.getElementById('chat-window');
    if (chatWindow && chatWindow.scrollTop == 0) {
      this.pageNumber += 1;
      this.getMessages(false);
    }
  }

  scrollToBottom() {
    const chatWindow = document.getElementById('chat-window');
    if (chatWindow) {
      chatWindow.scrollTop = chatWindow.scrollHeight;
    }
  }

  maintainScollPosition() {
    const chatWindow = document.getElementById('chat-window');
    if (chatWindow) {
      chatWindow.scrollTop = chatWindow.scrollHeight - this.currentScrollHeight;
    }
  }
}
