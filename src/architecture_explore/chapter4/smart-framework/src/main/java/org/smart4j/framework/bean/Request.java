package org.smart4j.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author jack
 * @since 1.0.0
 * */
public class Request {

    private String requestMethod;

    private String requestPath;

    public Request(String requestMethod, String requestPath){
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod(){
        return this.requestMethod;
    }

    public String getRequestPath(){
        return this.requestPath;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
