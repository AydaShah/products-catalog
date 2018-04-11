package com.aydashah.productscatalog.app.model.response;

import com.aydashah.productscatalog.app.model.ErrorMessages;

/**
 * Created by AydaShah on 4/11/18.
 */

public class ErrorResponse extends BaseResponse {

    private ErrorMessages messages;

    public ErrorMessages getMessages() {
        return messages;
    }

    public void setMessages(ErrorMessages messages) {
        this.messages = messages;
    }
}
