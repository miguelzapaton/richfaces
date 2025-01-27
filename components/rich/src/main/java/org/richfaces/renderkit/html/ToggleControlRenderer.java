/*
 * JBoss, Home of Professional Open Source
 * Copyright ${year}, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.richfaces.renderkit.html;

import jakarta.faces.application.ResourceDependencies;
import jakarta.faces.application.ResourceDependency;
import jakarta.faces.component.behavior.ClientBehavior;
import jakarta.faces.component.behavior.ClientBehaviorContext;
import jakarta.faces.render.ClientBehaviorRenderer;

import org.richfaces.cdk.annotations.JsfBehaviorRenderer;
import org.richfaces.component.behavior.ToggleControl;

/**
 * @author akolonitsky
 *
 */
@ResourceDependencies({ @ResourceDependency(library = "org.richfaces", name = "jquery.js"), @ResourceDependency(library = "org.richfaces", name = "richfaces.js") })
@JsfBehaviorRenderer(type = "org.richfaces.component.behavior.ToggleControl")
public class ToggleControlRenderer extends ClientBehaviorRenderer {
    @Override
    public String getScript(ClientBehaviorContext behaviorContext, ClientBehavior behavior) {
        ToggleControl control = (ToggleControl) behavior;

        StringBuilder builder = new StringBuilder();
        builder.append("RichFaces.component('").append(control.getPanelId(behaviorContext)).append("').switchToItem('")
            .append(control.getTargetItem()).append("'); return ").append(!control.getDisableDefault()).append(';');

        return builder.toString();
    }
}