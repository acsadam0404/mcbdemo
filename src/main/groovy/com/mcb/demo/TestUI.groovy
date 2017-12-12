package com.mcb.demo

import com.vaadin.annotations.PropertyId
import com.vaadin.annotations.Theme
import com.vaadin.data.BeanValidationBinder
import com.vaadin.data.Binder
import com.vaadin.data.provider.CallbackDataProvider
import com.vaadin.data.provider.DataProvider
import com.vaadin.data.provider.Query
import com.vaadin.server.ThemeResource
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.Button
import com.vaadin.ui.ComboBox
import com.vaadin.ui.Grid
import com.vaadin.ui.Image
import com.vaadin.ui.InlineDateField
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
import com.vaadin.ui.PasswordField
import com.vaadin.ui.TextField
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme
import org.springframework.beans.factory.annotation.Autowired

import java.util.stream.Stream

/**
 * Created by aacs on 2017. 12. 12..
 */
@SpringUI
@Theme("susan")
class TestUI extends UI {

    @PropertyId(Club.NAME)
    private TextField nameField
    @PropertyId(Club.COLOUR)
    private TextField colourField

    private InlineDateField inlineDateField

    @Autowired
    private ClubService service

    private MeruemBinder<Club> binder

    private Grid grid

    @Override
    protected void init(VaadinRequest request) {
        def v = new VerticalLayout()
        def title = new Label("Hello")
        title.addStyleName(ValoTheme.LABEL_H1)
        v.addComponent(title)
        nameField = new TextField("Name")
        nameField.addStyleName("blackfield")
        colourField = new TextField("Colour")
        v.addComponent(nameField)
        v.addComponent(colourField)
        v.addComponent(new Image("image", new ThemeResource("images/logo.png")))
        v.addComponent(new Button("Save", new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent event) {
                save()
            }
        }))

        v.addComponent(inlineDateField = new InlineDateField("Datepicker"))
        v.addComponent(new PasswordField("password"))
        v.addComponent(new ComboBox<String>("Select", Arrays.asList("first", "second")))
        grid = new Grid(Club.class)
        grid.getColumn("metaClass").hidden = true
        grid.setDataProvider(DataProvider.fromCallbacks(new CallbackDataProvider.FetchCallback() {
            @Override
            Stream fetch(Query query) {
                return service.findAll().stream()
            }
        }, new CallbackDataProvider.CountCallback() {
            @Override
            int count(Query query) {
                return service.findAll().size()
            }
        }))
        grid.addItemClickListener({ row ->
            binder.bean = row.item
        })

        v.addComponent(grid)

        binder = new MeruemBinder<>(new Club())
        binder.bindInstanceFields(this)
        v.addComponent(new LoginPage())
        setContent(v)
    }

    private void save() {
        binder.writeBean(binder.bean)
        service.save(binder.bean)
        Notification.show("Club saved: ${binder.bean.toString()}")
        grid.getDataProvider().refreshAll();
        binder.bean = new Club()
        nameField.focus()
    }
}
