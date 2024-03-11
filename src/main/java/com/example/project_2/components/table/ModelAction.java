package com.example.project_2.components.table;


public class ModelAction<T> {

    public T getObject() {
        return t;
    }

    public void setObject(T t) {
        this.t = t;
    }

    public EventAction<T> getEvent() {
        return event;
    }

    public void setEvent(EventAction<T> event) {
        this.event = event;
    }

    public ModelAction(T t, EventAction<T> event) {
        this.t = t;
        this.event = event;
    }

    public ModelAction() {
    }

    private T t;
    private EventAction event;
}
