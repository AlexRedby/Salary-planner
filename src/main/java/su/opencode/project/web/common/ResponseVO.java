package su.opencode.project.web.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVO implements Serializable {
    public static final ResponseVO SUCCESS = new ResponseVO();
    private static final long serialVersionUID = 9011133005493854807L;
    private boolean success;
    private Serializable msg;
    private Serializable fullException;
    private long total;
    private Serializable result;
    private Long serverDateTime = System.currentTimeMillis();

    public ResponseVO() {
        success = true;
    }

    public ResponseVO(String s) {
        this.success = true;
        this.msg = s;
    }

    public ResponseVO(Boolean value) {
        this.success = true;
        this.result = value;
    }

    public ResponseVO(boolean success, Serializable msg) {
        this.success = success;
        this.msg = msg;
    }

    public ResponseVO(List result) {
        this.success = true;
        this.total = result.size();
        this.result = result.toArray();
    }

    public ResponseVO(long total, List result) {
        this.success = true;
        this.total = total;
        this.result = result.toArray();
    }

    public ResponseVO(long total) {
        this.success = true;
        this.total = 0;
        this.result = (Serializable) Collections.emptyList();
    }

    public ResponseVO(List result, Serializable msg) {
        this.success = true;
        this.total = result.size();
        this.result = result.toArray();
        this.msg = msg;
    }

    public ResponseVO(boolean success, Serializable result, Serializable msg) {
        this.success = success;
        this.result = result;
        this.msg = msg;
    }

    public ResponseVO(boolean success, Serializable result, Serializable msg, Serializable fullException) {
        this.success = success;
        this.result = result;
        this.msg = msg;
        this.fullException = fullException;
    }

    public ResponseVO(Serializable result) {
        this.success = true;
        this.total = 1;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Serializable getMsg() {
        return msg;
    }

    public void setMsg(Serializable msg) {
        this.msg = msg;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Serializable getResult() {
        return result;
    }

    public void setResult(Serializable result) {
        this.result = result;
    }

    public Long getServerDateTime() {
        return serverDateTime;
    }

    public void setServerDateTime(final Long serverDateTime) {
        this.serverDateTime = serverDateTime;
    }

    public Serializable getFullException() {
        return fullException;
    }

    public void setFullException(final Serializable fullException) {
        this.fullException = fullException;
    }

}
