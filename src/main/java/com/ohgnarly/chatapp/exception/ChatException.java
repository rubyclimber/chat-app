package com.ohgnarly.chatapp.exception;

public class ChatException extends Exception {
    public ChatException() {super();}

    public ChatException(String message) {super(message);}

    public ChatException(String message, Throwable cause) {super(message, cause);}

    public ChatException(Throwable cause) {super(cause);}
}
