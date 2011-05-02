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

package org.jboss.as.console.client.core;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Heiko Braun
 * @date 5/2/11
 */
public interface UIConstants extends Constants {

    String common_error_unexpectedHttpResponse();
    String common_error_detailsMissing();

    String common_label_hostManagement();
    String common_label_profileManagement();
    String common_label_groupManagement();

    String common_label_serverInstances();

    String common_label_serverStatus();

    String common_label_serverGroup();

    String common_label_status();

    String common_label_server();

    String common_label_serverConfig();

    String common_label_serverInstance();

    String common_label_instanceDetails();

    String common_label_virtualMachine();

    String common_label_serverConfigs();

    String common_label_noRecords();

    String common_label_hostConfiguration();

    String common_label_systemProperties();

    String common_label_socketBindingGroups();

    String common_label_virtualMachines();

    String common_label_paths();
}