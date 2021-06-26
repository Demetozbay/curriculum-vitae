package com.demet.curriculumvitae.exception;

import com.demet.curriculumvitae.exception.handler.BaseException;

public class NotFound extends BaseException {
    private static final int status = 404;
    private static final String reason = "NotFound";

    public NotFound(String parameter) {
        super(status, reason, parameter,
              "The request failed because a resource associated with the request could not be found.");
    }
}
