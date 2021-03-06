/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */

package org.jboss.as.console.client.shared.subsys.jca;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.as.console.client.Console;
import org.jboss.as.console.client.shared.help.FormHelpPanel;
import org.jboss.as.console.client.shared.subsys.Baseadress;
import org.jboss.as.console.client.shared.subsys.jca.model.DataSource;
import org.jboss.as.console.client.shared.subsys.jca.model.XADataSource;
import org.jboss.ballroom.client.widgets.forms.DisclosureGroupRenderer;
import org.jboss.ballroom.client.widgets.forms.Form;
import org.jboss.ballroom.client.widgets.forms.PasswordBoxItem;
import org.jboss.ballroom.client.widgets.forms.StatusItem;
import org.jboss.ballroom.client.widgets.forms.TextBoxItem;
import org.jboss.ballroom.client.widgets.forms.TextItem;
import org.jboss.ballroom.client.widgets.tools.ToolButton;
import org.jboss.ballroom.client.widgets.tools.ToolStrip;
import org.jboss.ballroom.client.widgets.window.Feedback;
import org.jboss.dmr.client.ModelNode;

/**
 * @author Heiko Braun
 * @date 5/4/11
 */
public class XADataSourceDetails {

    private Form<XADataSource> form;
    private ToolButton editBtn;
    private DataSourcePresenter presenter;

    public XADataSourceDetails(DataSourcePresenter presenter) {
        this.presenter = presenter;
        form = new Form(XADataSource.class);
        form.setNumColumns(2);
    }

    public Widget asWidget() {

        ToolStrip detailToolStrip = new ToolStrip();
        editBtn = new ToolButton(Console.CONSTANTS.common_label_edit());
        ClickHandler editHandler = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                if(null==form.getEditedEntity())
                    return;

                if(editBtn.getText().equals(Console.CONSTANTS.common_label_edit()))
                    presenter.onEditXA(form.getEditedEntity());
                else
                    presenter.onSaveXADetails(form.getEditedEntity().getName(), form.getChangedValues());
            }
        };
        editBtn.addClickHandler(editHandler);
        detailToolStrip.addToolButton(editBtn);


        ClickHandler clickHandler = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                DataSource currentSelection = form.getEditedEntity();

                Feedback.confirm(
                        "Delete DataSource",
                        "Really delete this DataSource '" + currentSelection.getName() + "' ?",
                        new Feedback.ConfirmationHandler() {
                            @Override
                            public void onConfirmation(boolean isConfirmed) {
                                if (isConfirmed) {
                                    presenter.onDeleteXA(form.getEditedEntity());
                                }
                            }
                        });
            }
        };
        ToolButton deleteBtn = new ToolButton(Console.CONSTANTS.common_label_delete());
        deleteBtn.addClickHandler(clickHandler);
        detailToolStrip.addToolButton(deleteBtn);


        ClickHandler disableHandler = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                String state = form.getEditedEntity().isEnabled() ? Console.CONSTANTS.common_label_disable() : Console.CONSTANTS.common_label_enable();
                final boolean doEnable = !form.getEditedEntity().isEnabled();
                Feedback.confirm(state + " datasource", "Do you want to " + state + " this DataSource?",
                        new Feedback.ConfirmationHandler() {
                            @Override
                            public void onConfirmation(boolean isConfirmed) {
                                if (isConfirmed) {
                                    presenter.onDisableXA(form.getEditedEntity(), doEnable);
                                }
                            }
                        });
            }
        };

        ToolButton enableBtn = new ToolButton(Console.CONSTANTS.common_label_enOrDisable());
        enableBtn.addClickHandler(disableHandler);
        detailToolStrip.addToolButtonRight(enableBtn);

        VerticalPanel panel = new VerticalPanel();
        panel.add(detailToolStrip);

        final TextItem nameItem = new TextItem("name", "Name");
        TextBoxItem jndiItem = new TextBoxItem("jndiName", "JNDI");
        StatusItem enabledFlagItem = new StatusItem("enabled", "Is enabled?");
        TextItem driverItem = new TextItem("driverName", "Driver");

        TextBoxItem userItem = new TextBoxItem("username", "Username");
        PasswordBoxItem passwordItem = new PasswordBoxItem("password", "Password");

        form.setFields(nameItem, jndiItem, enabledFlagItem, driverItem);
        form.setFieldsInGroup("Connection", new DisclosureGroupRenderer(), userItem, passwordItem);

        form.setEnabled(false); // currently not editable


        final FormHelpPanel helpPanel = new FormHelpPanel(
                new FormHelpPanel.AddressCallback() {
                    @Override
                    public ModelNode getAddress() {
                        ModelNode address = Baseadress.get();
                        address.add("subsystem", "datasources");
                        address.add("xa-data-source", "*");
                        return address;
                    }
                }, form
        );
        panel.add(helpPanel.asWidget());

        Widget formWidget = form.asWidget();
        panel.add(formWidget);

        return panel;
    }


    public void setEnabled(boolean b) {
        form.setEnabled(b);

        if(b)
            editBtn.setText("Save");
        else
            editBtn.setText("Edit");
    }

    public void setSelectedRecord(XADataSource dataSource) {
        form.edit(dataSource);
    }
}
