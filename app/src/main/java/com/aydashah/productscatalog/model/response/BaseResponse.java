package com.aydashah.productscatalog.model.response;

import com.aydashah.productscatalog.model.Session;

/**
 * Created by AydaShah on 4/11/18.
 */

public class BaseResponse {

    private boolean success;
    private Session session;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
