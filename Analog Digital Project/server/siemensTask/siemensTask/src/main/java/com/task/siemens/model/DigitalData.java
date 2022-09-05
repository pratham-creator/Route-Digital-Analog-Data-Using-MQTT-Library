package com.task.siemens.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DigitalData {

    private boolean value;

    public DigitalData() {
        super();
    }

    public DigitalData(boolean value) {
        super();
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
