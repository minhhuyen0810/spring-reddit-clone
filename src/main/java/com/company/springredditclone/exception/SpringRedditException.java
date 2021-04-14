package com.company.springredditclone.exception;

public class SpringRedditException extends Throwable {
    public SpringRedditException(String messeage, Exception e){
        super(messeage);
    }
    public SpringRedditException(String exMessage) {
        super(exMessage);
    }
}
