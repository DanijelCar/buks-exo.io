package com.buks.util;

public class ApplicationException extends RuntimeException {

    private int errorStatusCode;
    private String errorMessageKey;
    private String errorDescription;

    private ApplicationException(String message) {
        super(message);
    }

    public int getErrorStatusCode() {
        return errorStatusCode;
    }

    public String getErrorMessageKey() {
        return errorMessageKey;
    }

    public String getErrorDescription() {
        return errorDescription;
    }


    static final class ApplicationExceptionResponseObject {
        private int errorStatusCode;
        private String errorMessage;
        private String errorDescription;

        private ApplicationExceptionResponseObject(int errorStatusCode, String errorMessage, String errorDescription) {
            this.errorDescription = errorDescription;
            this.errorMessage = errorMessage;
            this.errorStatusCode = errorStatusCode;
        }

        static ApplicationExceptionResponseObject from(ApplicationException applicationException) {
            return new ApplicationExceptionResponseObject(applicationException.getErrorStatusCode(), applicationException.getErrorMessageKey(), applicationException.getErrorDescription());
        }

        public int getErrorStatusCode() {
            return errorStatusCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public String getErrorDescription() {
            return errorDescription;
        }
    }


    public static final class ApplicationExceptionBuilder {
        private int errorStatusCode;
        private String errorMessageKey;
        private String errorDescription;

        private ApplicationExceptionBuilder() {
        }

        public static ApplicationExceptionBuilder anApplicationException() {
            return new ApplicationExceptionBuilder();
        }

        public ApplicationExceptionBuilder withErrorStatusCode(int errorStatusCode) {
            this.errorStatusCode = errorStatusCode;
            return this;
        }

        public ApplicationExceptionBuilder withErrorMessageKey(String errorMessageKey) {
            this.errorMessageKey = errorMessageKey;
            return this;
        }

        public ApplicationExceptionBuilder withErrorDescription(String errorDescription) {
            this.errorDescription = errorDescription;
            return this;
        }

        public void build() {
            ApplicationException applicationException = new ApplicationException("");
            applicationException.errorMessageKey = this.errorMessageKey;
            applicationException.errorStatusCode = this.errorStatusCode;
            applicationException.errorDescription = this.errorDescription;
            throw applicationException;
        }
    }
}
