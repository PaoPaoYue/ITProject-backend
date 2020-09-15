package com.ypp.itproject.vo.util;

import java.io.Serializable;

public class SuccessWapper implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;

    public SuccessWapper() {
    }

    public SuccessWapper(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "SuccessWapper{" +
                "success=" + success +
                '}';
    }
}
