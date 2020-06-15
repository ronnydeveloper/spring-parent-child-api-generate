package id.co.ptap.circleaf.core.dto;

import id.co.ptap.circleaf.core.enums.ResponseCode;

public class ApiResponse<T> {
    String responseCode;
    String responseMessage;
    T data;

    public ApiResponse() {
        this.responseCode = ResponseCode._100.value;
        this.responseMessage = ResponseCode._100.text;
    }

    public ApiResponse(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public void setResponseCodeEnum(ResponseCode responseCodeEnum) {
        this.responseCode = responseCodeEnum.getValue();
        this.responseMessage = responseCodeEnum.getText();
    }

    public void setResponseCode(final String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseMessage(final String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public T getData() {
        return this.data;
    }
}
