package com.demet.curriculumvitae.exception;

import com.demet.curriculumvitae.exception.handler.BaseException;

public class AlreadyExists extends BaseException {
    private static final int status = 409;
    private static final String reason = "AlreadyExists";

    public AlreadyExists(String parameter) {
        super(status, reason, parameter,
              "The request failed because the requested operation would conflict with an existing item.");
    }
}
