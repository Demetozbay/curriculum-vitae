package com.demet.curriculumvitae.exception.handler;

public class BaseException extends Throwable {
    public final int status;
    public final String reason;
    public final String parameter;
    public final String cause;

    public BaseException(int status, String reason, String parameter, String cause) {
        this.status = status;
        this.reason = reason;
        this.parameter = parameter;
        this.cause = cause;
    }
}
