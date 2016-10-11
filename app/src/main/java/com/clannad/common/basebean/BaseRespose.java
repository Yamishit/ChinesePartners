package com.clannad.common.basebean;

import java.io.Serializable;

/**
 * des:封装服务器返回数据
 */
public class BaseRespose<T> implements Serializable {
    public String code;
    public String msg;

    public T data;

    /**
     * 根据服务器返回code判别.  需要修改
     * @return
     */
    public boolean success() {
        return "1".equals(code);
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
