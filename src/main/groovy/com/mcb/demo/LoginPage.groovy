package com.mcb.demo

import com.vaadin.ui.Button
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.PasswordField
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout

/**
 * Created by aacs on 2017. 12. 12..
 */
class LoginPage extends CustomComponent{
    LoginPage() {
        def root = new VerticalLayout()
        root.addComponent(new TextField("Username"))
        root.addComponent(new PasswordField("Password"))
        root.addComponent(new Button("Login"))
        setCompositionRoot(root)
    }
}
