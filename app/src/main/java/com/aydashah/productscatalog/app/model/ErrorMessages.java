package com.aydashah.productscatalog.app.model;

import java.util.ArrayList;

/**
 * Created by AydaShah on 4/11/18.
 */

public class ErrorMessages {

    private ArrayList<Error> error;

    public ArrayList<Error> getError() {
        return error;
    }

    public void setError(ArrayList<Error> error) {
        this.error = error;
    }

    class Error {
        private String reason;
        private String message;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
