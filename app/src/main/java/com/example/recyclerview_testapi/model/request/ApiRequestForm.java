package com.example.recyclerview_testapi.model.request;

public class ApiRequestForm {
    private String KEY_WORD;
    private String DATE;
    private String PUBLISHED;
    private String API_KEY;

    public ApiRequestForm() {
    }

    public ApiRequestForm(String CURRENCY, String DATE, String PUBLISHED, String API_KEY) {
        this.KEY_WORD = CURRENCY;
        this.DATE = DATE;
        this.PUBLISHED = PUBLISHED;
        this.API_KEY = API_KEY;
    }

    public String getCURRENCY() {
        return KEY_WORD;
    }

    public void setCURRENCY(String CURRENCY) {
        this.KEY_WORD = CURRENCY;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getPUBLISHED() {
        return PUBLISHED;
    }

    public void setPUBLISHED(String PUBLISHED) {
        this.PUBLISHED = PUBLISHED;
    }

    public String getAPI_KEY() {
        return API_KEY;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }
}
