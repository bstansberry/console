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
package org.jboss.as.console.client.shared.subsys.logging.model;

import java.util.List;

/**
 * Model for a Logger
 *
 * @author Stan Silvert ssilvert@redhat.com (C) 2011 Red Hat Inc.
 */
public interface LoggerConfig {
   String getName();
   void setName(String name);
   
   String getLevel();
   void setLevel(String level);
   
   List<String> getHandlers();
   void setHandlers(List<String> handlers);
   
   /**
    * Get the handler that needs to be assigned to the logger.
    * @param handlerName 
    */
   String getHandlerToAssign();
   
   /**
    * Set a handler that the presenter will try to assign to the logger.
    * @param handlerName The handler name.
    */
   void setHandlerToAssign(String handlerName);
   
   /**
    * Get the handler that needs to be assigned to the logger.
    * @param handlerName 
    */
   String getHandlerToUnassign();
   
   /**
    * Set a handler that the presenter will try to assign to the logger.
    * @param handlerName The handler name.
    */
   void setHandlerToUnassign(String handlerName);
}
