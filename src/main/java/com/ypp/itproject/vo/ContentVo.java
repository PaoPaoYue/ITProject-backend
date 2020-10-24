package com.ypp.itproject.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ypp.itproject.util.LocalDateTimeConverter;

import java.time.LocalDateTime;

public abstract class ContentVo {

    @JsonSerialize(using = LocalDateTimeConverter.class)
    LocalDateTime lastUpdate;

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
