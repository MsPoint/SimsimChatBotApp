package com.example.muamarsidik.simsimichatbot.model;

/**
 * Created by Publish on 9/15/2017.
 */

public class SimsimModel {
    public String response;
    public String id;
    public int result;
    public String msg;

    public SimsimModel(String response, String id, int result, String msg) {
        this.response = response;
        this.id = id;
        this.result = result;
        this.msg = msg;
    }

    public SimsimModel() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
