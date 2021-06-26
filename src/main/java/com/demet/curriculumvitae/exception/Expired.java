package com.demet.curriculumvitae.exception;

import com.demet.curriculumvitae.exception.handler.BaseException;

public class Expired extends BaseException {
    private static final int status = 400;
    private static final String reason = "Expired";

    public Expired(String parameter) {
        super(status, reason, parameter,
              "The request failed because the requested operation contains expired item.");
    }
}
