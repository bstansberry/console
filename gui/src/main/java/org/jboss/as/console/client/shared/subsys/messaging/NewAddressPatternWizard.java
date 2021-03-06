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

package org.jboss.as.console.client.shared.subsys.messaging;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.as.console.client.shared.subsys.messaging.model.AddressingPattern;
import org.jboss.as.console.client.shared.subsys.messaging.model.MessagingProvider;
import org.jboss.as.console.client.shared.subsys.messaging.model.SecurityPattern;
import org.jboss.ballroom.client.widgets.forms.Form;
import org.jboss.ballroom.client.widgets.forms.FormValidation;
import org.jboss.ballroom.client.widgets.forms.NumberBoxItem;
import org.jboss.ballroom.client.widgets.forms.TextBoxItem;
import org.jboss.ballroom.client.widgets.window.DialogueOptions;
import org.jboss.ballroom.client.widgets.window.WindowContentBuilder;

/**
 * @author Heiko Braun
 * @date 5/10/11
 */
public class NewAddressPatternWizard {

    private MessagingPresenter presenter;
    private MessagingProvider provider;

    public NewAddressPatternWizard (MessagingPresenter presenter, MessagingProvider provider) {
        this.presenter = presenter;
        this.provider = provider;
    }


    public Widget asWidget() {

        VerticalPanel layout = new VerticalPanel();
        layout.setStyleName("window-content");
        layout.getElement().setAttribute("cellpadding", "10");

        layout.add(new HTML("<h3>Create Address Pattern</h3>"));

        final Form<SecurityPattern> form = new Form<SecurityPattern>(SecurityPattern.class);


        TextBoxItem pattern = new TextBoxItem("pattern", "Pattern");

        TextBoxItem dlQ = new TextBoxItem("deadLetterQueue", "Dead Letter Queue");
        TextBoxItem expQ= new TextBoxItem("expiryQueue", "Expiry Queue");
        NumberBoxItem redelivery = new NumberBoxItem("redeliveryDelay", "Redelivery Delay");

        form.setFields(pattern, dlQ, expQ, redelivery);

        // defaults
        AddressingPattern defaultPattern = findDefaultPattern();
        if(defaultPattern!=null) {
            dlQ.setValue(defaultPattern.getDeadLetterQueue());
            expQ.setValue(defaultPattern.getExpiryQueue());
            redelivery.setValue(defaultPattern.getRedeliveryDelay());
        }
        layout.add(form.asWidget());

        DialogueOptions options = new DialogueOptions(
                new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        //submit
                        FormValidation validation = form.validate();
                        if(!validation.hasErrors())
                        {
                            presenter.onCreateAddressPattern(form.getUpdatedEntity());
                        }
                    }
                },
                new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        // cancel
                        presenter.closeDialogue();
                    }
                }
        );

        return new WindowContentBuilder(layout, options).build();
    }

    private AddressingPattern findDefaultPattern() {
        AddressingPattern match = null;
        for(AddressingPattern pattern : provider.getAddressPatterns()) {
            if("#".equals(pattern.getPattern()))
            {
                match = pattern;
                break;
            }
        }

        return match;
    }
}
