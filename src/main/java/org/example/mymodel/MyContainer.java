package org.example.mymodel;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MyContainer implements Container {
    private Map<Object, Value> propertyIds = new HashMap();
    private int size = 0;
    private Map<Object, Item> items = new HashMap<>();

    @Override
    public Item getItem(Object o) {
        return items.get(o);
    }

    @Override
    public Collection<?> getContainerPropertyIds() {
        return Collections.unmodifiableSet(propertyIds.keySet());
    }

    @Override
    public Collection<?> getItemIds() {
        return Collections.unmodifiableSet(items.keySet());
    }

    @Override
    public Property getContainerProperty(java.lang.Object itemId, java.lang.Object propertyId) {
        return items.get(itemId).getItemProperty(propertyId);
    }

    @Override
    public Class<?> getType(Object o) {
        return MyProperty.class;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsId(Object o) {
        return false;
    }

    @Override
    public Item addItem(Object id) throws UnsupportedOperationException {
        Item item = new MyItem();

        for (Map.Entry<Object, Value> x : propertyIds.entrySet()) {
            Value value = x.getValue();

            Property p = new MyProperty(value.type);
            p.setValue(value.defaultValue);

            item.addItemProperty(x.getKey(), p);
        }

        items.put(id, item);
        size++;
        return item;
    }

    @Override
    public Object addItem() throws UnsupportedOperationException {
        UUID id = UUID.randomUUID();
        addItem(id);
        return id;
    }

    @Override
    public boolean removeItem(Object o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue) throws UnsupportedOperationException {
        if (propertyIds.containsKey(propertyId)) {
            return false;
        } else {
            Value typedef = new Value();

            typedef.type = type;
            typedef.defaultValue = defaultValue;

            propertyIds.put(propertyId, typedef);

            return true;
        }
    }

    @Override
    public boolean removeContainerProperty(Object o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAllItems() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public static class Value {
        private Class<?> type;
        private Object defaultValue;
    }
}
