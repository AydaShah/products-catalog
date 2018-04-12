package com.aydashah.productscatalog.model;

/**
 * Created by AydaShah on 4/11/18.
 */

public class Session {

    private String id;
    private Object expire;
    private String YII_CSRF_TOKEN;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getExpire() {
        return expire;
    }

    public void setExpire(Object expire) {
        this.expire = expire;
    }

    public String getYII_CSRF_TOKEN() {
        return YII_CSRF_TOKEN;
    }

    public void setYII_CSRF_TOKEN(String YII_CSRF_TOKEN) {
        this.YII_CSRF_TOKEN = YII_CSRF_TOKEN;
    }
}
