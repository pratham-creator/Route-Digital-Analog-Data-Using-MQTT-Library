package com.task.siemens.model;

public class AnalogData {
    private float value;

    public AnalogData() {
        super();
    }

    public AnalogData(float v) {
        super();
        value=v;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
