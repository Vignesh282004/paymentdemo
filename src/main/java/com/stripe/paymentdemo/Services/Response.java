package com.stripe.paymentdemo.Services;

public class Response {

    private String info;
    private boolean status;

    public Response() {}

    public Response(boolean status,String info) {
        this.status = status;
        this.info = info;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    
  @Override
    public String toString() {
        return "Response [info=" + info + ", status=" + status + "]";
    }
}
