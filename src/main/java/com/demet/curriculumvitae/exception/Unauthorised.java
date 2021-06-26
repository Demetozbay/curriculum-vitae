package com.demet.curriculumvitae.exception;

import com.demet.curriculumvitae.exception.handler.BaseException;

public class Unauthorised extends BaseException {
    private static final int status = 403;
    private static final String reason = "Unauthorised";

    public Unauthorised(String parameter) {
        super(status, reason, parameter,
              "The request failed because the account don't have required permissions.");
    }
}
