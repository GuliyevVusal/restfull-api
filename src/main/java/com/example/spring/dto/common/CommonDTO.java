package com.example.spring.dto.common;

import java.time.LocalDateTime;

public class CommonDTO {

    private Object obj;
    private String message;
    private LocalDateTime dateTime;


    public Object getObj() {
        return obj;
    }

    public CommonDTO setObj(Object obj) {
        this.obj = obj;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public CommonDTO setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
