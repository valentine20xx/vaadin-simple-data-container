package org.example.mymodel;

import com.vaadin.data.Item;
import com.vaadin.data.Property;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class MyItem implements Item {
    private Map<Object, Property> map = new HashMap();

    @Override
    public Property getItemProperty(Object o) {
        return map.get(o);
    }

    @Override
    public Collection<?> getItemPropertyIds() {
        return Collections.unmodifiableSet(map.keySet());
    }

    @Override
    public boolean addItemProperty(Object id, Property property) throws UnsupportedOperationException {
        if (map.keySet().contains(id)) {
            return false;
        } else {
            map.put(id, property);
            return true;
        }
    }

    @Override
    public boolean removeItemProperty(Object id) throws UnsupportedOperationException {
        if (map.keySet().contains(id)) {
            map.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
