package org.example.mymodel;

import com.vaadin.data.Property;

public class MyProperty<T> implements Property<T> {
    private T value;
    private Class<T> type;

    public MyProperty(Class<T> cls) {
        type = cls;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T o) throws ReadOnlyException {
        value = o;
    }

    @Override
    public Class<? extends T> getType() {
        return type;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public void setReadOnly(boolean b) {

    }
}
