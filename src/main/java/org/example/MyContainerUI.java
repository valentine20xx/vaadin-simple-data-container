package org.example;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.example.mymodel.MyContainer;

import javax.servlet.annotation.WebServlet;
import java.util.UUID;

@Push
@Theme("reindeer")
public class MyContainerUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout verticalLayout = new VerticalLayout();
        final Table table = new Table("Table with MyContainer");

        Container myContainer = new MyContainer();

        myContainer.addContainerProperty("key", String.class, "Defaullt key");
        myContainer.addContainerProperty("value", Double.class, 0.0);

        myContainer.addItem();

        Object itemId = myContainer.addItem();
        Item item = myContainer.getItem(itemId);
        Property<String> nameProperty = item.getItemProperty("key");
        nameProperty.setValue("key1");
        Property<Double> volumeProperty = item.getItemProperty("value");
        volumeProperty.setValue(100.0);

        myContainer.getContainerProperty(itemId, "value").setValue(5.0);

        item = myContainer.addItem(UUID.randomUUID());
        item.getItemProperty("key").setValue("key2");
        item.getItemProperty("value").setValue(119.2);

        table.setContainerDataSource(myContainer);
        table.setWidth(100, Unit.PERCENTAGE);

        verticalLayout.addComponent(table);
        verticalLayout.setMargin(true);

        setContent(verticalLayout);
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyContainerUI.class)
    public static class Servlet extends VaadinServlet {
    }
}