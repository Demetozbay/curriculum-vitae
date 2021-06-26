package com.demet.curriculumvitae.exception;

import com.demet.curriculumvitae.exception.handler.BaseException;

public class Unavailable extends BaseException {
    private static final int status = 503;
    private static final String reason = "Unavailable";

    public Unavailable(String parameter) {
        super(status, reason, parameter,
              "The request failed because a backend error occurred while processing the requested operation.");
    }
}
