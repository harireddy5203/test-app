/*
 * Copyright (c) 2021 REPLACE_CUSTOMER_NAME. All rights reserved.
 *
 * This file is part of test-app.
 *
 * test-app project and associated code cannot be copied
 * and/or distributed without a written permission of REPLACE_CUSTOMER_NAME,
 * and/or its subsidiaries.
 */
package com.test.commons.web.api.error;

import com.test.commons.error.ErrorMessages;
import com.test.commons.error.IError;
import com.test.commons.error.IErrorMessages;

/**
 * Enum constants that represent the API error codes and messages that can be used across the application.
 * <p>
 * For more details, see the documentation on {@link IError} contract.
 *
 * @author Mahalingam Iyer
 */
public enum ApiErrors implements IError {
    // NOTE:
    // Whenever a new constant is added here, ensure that the error message for the same constant is added in
    // src/main/resources/l10n/api_error_messages.properties

    ACCESS_DENIED,
    DATA_INTEGRITY_VIOLATION,
    FAILED_TO_DOWNLOAD_FILE,
    FAILED_TO_TRANSFER_FILE,
    GENERIC_ERROR,
    HTTP_MESSAGE_NOT_READABLE,
    INVALID_DATA_ACCESS;

    /** Reference to {@link IErrorMessages}, which holds the error messages. */
    private static final ErrorMessages ERROR_MESSAGES = ErrorMessages.instance("l10n/api_error_messages", ApiErrors.class.getClassLoader());

    @Override
    public IErrorMessages getErrorMessages() {
        return ApiErrors.ERROR_MESSAGES;
    }
}
