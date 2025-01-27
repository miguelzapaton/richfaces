/**
 * License Agreement.
 *
 * Rich Faces - Natural Ajax for Java Server Faces (JSF)
 *
 * Copyright (C) 2007 Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */
package org.richfaces.component;

import jakarta.faces.component.UICommand;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;

import org.richfaces.cdk.annotations.Attribute;
import org.richfaces.cdk.annotations.JsfComponent;
import org.richfaces.cdk.annotations.JsfRenderer;
import org.richfaces.cdk.annotations.Tag;
import org.richfaces.cdk.annotations.TagType;
import org.richfaces.component.attribute.AccesskeyProps;
import org.richfaces.component.attribute.AjaxCommandProps;
import org.richfaces.component.attribute.BypassProps;
import org.richfaces.component.attribute.CommandButtonProps;
import org.richfaces.component.attribute.CoreProps;
import org.richfaces.renderkit.AjaxConstants;

/**
 * <p>
 * The &lt;a4j:commandButton&gt; component is similar to the JavaServer Faces (JSF) &lt;h:commandButton&gt; component,
 * but additionally includes Ajax support.
 * </p>
 * @author Nick Belaevski
 */
@JsfComponent(renderer = @JsfRenderer(type = "org.richfaces.CommandButtonRenderer"), tag = @Tag(type = TagType.Facelets))
public abstract class AbstractCommandButton extends BasicActionComponent implements MetaComponentResolver, AccesskeyProps, AjaxCommandProps, BypassProps, CommandButtonProps, CoreProps {
    public static final String COMPONENT_TYPE = "org.richfaces.CommandButton";
    public static final String COMPONENT_FAMILY = UICommand.COMPONENT_FAMILY;

    /**
     * Ids of components that will participate in the "execute" portion of the Request Processing Lifecycle. Can be a single id,
     * a space or comma separated list of Id's, or an EL Expression evaluating to an array or Collection. Any of the keywords
     * "@this", "@form", "@all", "@none", "@region" may be specified in the identifier list. Some components make use of
     * additional keywords.<br/>
     * Default value is "@region" which resolves to form if no region is present.
     */
    @Attribute(generate = false)
    public Object getExecute() {
        return super.getExecute();
    }

    /**
     * Absolute or relative URL of the image to be displayed for this button. If specified, this "input" element will
     * be of type "image". Otherwise, it will be of the type specified by the "type" property with a label specified
     * by the "value" property
     */
    @Attribute
    public abstract String getImage();

    /**
     * This attribute specifies the position of the current element in the tabbing order for the current document.
     * This value must be a number between 0 and 32767. User agents should ignore leading zeros
     */
    @Attribute
    public abstract String getTabindex();

    public String resolveClientId(FacesContext facesContext, UIComponent contextComponent, String metaComponentId) {
        return null;
    }

    public String substituteUnresolvedClientId(FacesContext facesContext, UIComponent contextComponent, String metaComponentId) {

        if (AjaxContainer.META_COMPONENT_ID.equals(metaComponentId)) {
            return AjaxConstants.FORM;
        }

        return null;
    }
}
