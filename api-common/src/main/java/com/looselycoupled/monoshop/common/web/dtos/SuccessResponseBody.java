package com.looselycoupled.monoshop.common.web.dtos;

public class SuccessResponseBody {
    
    private int status;
    private String result;
    private String message;
    private Object data;
    
    public SuccessResponseBody(int status, String result, String message, Object data) {
        this.status = status;
        this.result = result;
        this.message = message;
        this.data = data;
    }
    
    public static Builder builder(){
        return new Builder();
    }
    
    @Override
    public String toString() {
        return "SuccessResponseBody{" + "status=" + status + ", result='" + result + '\'' + ", message='" + message + '\'' + ", data=" + data + '}';
    }
    
    public int getStatus() { return status; }
    public String getResult() { return result; }
    public String getMessage() { return message; }
    public Object getData() { return data; }
    
    public static class Builder{
        private int status;
        private String result;
        private String message;
        private Object data;
        
        public Builder status(int status){
            this.status = status;
            return this;
        }
    
        public Builder result(String result){
            this.result = result;
            return this;
        }
    
        public Builder message(String message){
            this.message = message;
            return this;
        }
    
        public Builder data(Object data){
            this.data = data;
            return this;
        }
        
        public SuccessResponseBody build(){
            return new SuccessResponseBody(this.status, this.result, this.message, this.data);
        }
    }
}
