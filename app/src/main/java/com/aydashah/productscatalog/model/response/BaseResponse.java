package com.aydashah.productscatalog.model.response;

import android.text.TextUtils;

import com.aydashah.productscatalog.model.ErrorMessages;
import com.aydashah.productscatalog.model.ErrorModel;
import com.aydashah.productscatalog.model.Session;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by AydaShah on 4/11/18.
 */

public class BaseResponse {

    private boolean success;
    private Session session;
    private ErrorMessages message;

    public ErrorMessages getMessages() {
        return message;
    }

    public void setMessages(ErrorMessages messages) {
        this.message = messages;
    }

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


    public static class MessagesDeserializer implements JsonDeserializer<BaseResponse> {

        @Override
        public BaseResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            BaseResponse baseResponse = new Gson().fromJson(json, BaseResponse.class);
            JsonObject jsonObject = json.getAsJsonObject();

            if (jsonObject.has("error")) {
                JsonElement elem = jsonObject.get("error");
                if (elem != null && !elem.isJsonNull()) {
                    String valuesString = elem.getAsString();
                    if (!TextUtils.isEmpty(valuesString)){
                        ArrayList<ErrorModel> error = new Gson().fromJson(valuesString, new TypeToken<ArrayList<ErrorModel>>() {}.getType());
                        ErrorMessages errorMessages=new ErrorMessages();
                        errorMessages.setError(error);

                        baseResponse.setMessages(errorMessages);
                    }
                }
            }
            return baseResponse ;
        }
    }
}
