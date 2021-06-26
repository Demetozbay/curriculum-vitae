package com.demet.curriculumvitae.exception;

import com.demet.curriculumvitae.exception.handler.BaseException;

public class UnableToProcess extends BaseException {
    private static final int status = 412;
    private static final String reason = "UnableToProcess";

    public UnableToProcess(String parameter) {
        super(status, reason, parameter,
              "The request failed because a service associated with the request unable to process the requested operation.");
    }
}
