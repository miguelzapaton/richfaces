/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and individual contributors
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
package org.richfaces.context;

import jakarta.faces.FacesException;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.visit.VisitCallback;
import jakarta.faces.component.visit.VisitContext;
import jakarta.faces.component.visit.VisitResult;
import jakarta.faces.context.FacesContext;

import org.richfaces.component.MetaComponentEncoder;
import org.richfaces.log.Logger;
import org.richfaces.log.RichfacesLogger;

/**
 * Wraps {@link VisitCallback} in order to be able execute own logic during {@link #visit(VisitContext, UIComponent)}
 * processing.
 *
 * This wrapper allows to process {@link MetaComponentEncoder} components.
 *
 * @author Nick Belaevski
 */
final class MetaComponentEncodingVisitCallback implements VisitCallback {

    private static final Logger LOG = RichfacesLogger.CONTEXT.getLogger();

    private FacesContext facesContext;
    private VisitCallback wrapped;

    MetaComponentEncodingVisitCallback(VisitCallback visitCallbackToWrap, FacesContext facesContext) {
        this.wrapped = visitCallbackToWrap;
        this.facesContext = facesContext;
    }

    /**
     * If context contains a {@link ExtendedVisitContext#META_COMPONENT_ID} a given target component will be treated as
     * {@link MetaComponentEncoder} and its {@link MetaComponentEncoder#encodeMetaComponent(FacesContext, String)} is
     * called.
     *
     * Otherwise the processor delegates to wrapped {@link VisitCallback} instance.
     */
    public VisitResult visit(VisitContext context, UIComponent target) {

        if (target instanceof MetaComponentEncoder) {
            String metaComponentId = (String) facesContext.getAttributes().get(ExtendedVisitContext.META_COMPONENT_ID);
            if (metaComponentId != null) {
                MetaComponentEncoder encoder = (MetaComponentEncoder) target;
                try {
                    encoder.encodeMetaComponent(facesContext, metaComponentId);
                } catch (Exception e) {
                    if (LOG.isErrorEnabled()) {
                        LOG.error(e.getMessage(), e);
                    }
                    throw new FacesException(String.format("exception thrown during encoding of meta-component %s@%s", target.getId(), metaComponentId), e);
                }
                // Once we visit a component, there is no need to visit
                // its children, since processDecodes/Validators/Updates and
                // encodeAll() already traverse the subtree. We return
                // VisitResult.REJECT to supress the subtree visit.
                return VisitResult.REJECT;
            }
        }
        return wrapped.visit(context, target);
    }
}