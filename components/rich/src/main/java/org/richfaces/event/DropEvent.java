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
package org.richfaces.event;

import jakarta.faces.component.UIComponent;
import jakarta.faces.event.FacesEvent;
import jakarta.faces.event.FacesListener;

/**
 * @author abelevich
 *
 */
public class DropEvent extends FacesEvent {
    private static final long serialVersionUID = -8093828320587434589L;
    private UIComponent dragSource;
    private Object dropValue;
    private Object dragValue;

    public DropEvent(UIComponent dropTarget, Object dropValue, UIComponent dragSource, Object dragValue) {
        super(dropTarget);

        this.dropValue = dropValue;
        this.dragSource = dragSource;
        this.dragValue = dragValue;
    }

    public UIComponent getDragSource() {
        return dragSource;
    }

    public UIComponent getDropTarget() {
        return getComponent();
    }

    public Object getDropValue() {
        return dropValue;
    }

    public Object getDragValue() {
        return dragValue;
    }

    @Override
    public boolean isAppropriateListener(FacesListener listener) {
        return (listener instanceof DropListener);
    }

    @Override
    public void processListener(FacesListener listener) {
        ((DropListener) listener).processDrop(this);
    }
}