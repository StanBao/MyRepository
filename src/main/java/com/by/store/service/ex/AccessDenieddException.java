package com.by.store.service.ex;

public class AccessDenieddException extends ServiceException{
    public AccessDenieddException() {
        super();
    }

    public AccessDenieddException(String message) {
        super(message);
    }

    public AccessDenieddException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDenieddException(Throwable cause) {
        super(cause);
    }

    protected AccessDenieddException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
