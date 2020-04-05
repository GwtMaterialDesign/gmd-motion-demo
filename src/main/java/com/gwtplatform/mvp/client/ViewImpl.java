
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gwtplatform.mvp.client;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.ui.*;
import com.gwtplatform.mvp.client.presenter.slots.IsSingleSlot;
import com.gwtplatform.mvp.client.presenter.slots.OrderedSlot;
import com.gwtplatform.mvp.client.presenter.slots.Slot;

import java.util.*;

public abstract class ViewImpl implements View {
    private final Map<Object, HasOneWidget> oneWidgetSlots = new HashMap();
    private final Map<Object, HasWidgets> hasWidgetSlots = new HashMap();
    private final Map<OrderedSlot<?>, List<Comparable<Comparable<?>>>> orderedSlots = new HashMap();
    private Widget widget;

    public ViewImpl() {
    }

    public void addToSlot(Object slot, IsWidget content) {
        if (this.hasWidgetSlots.containsKey(slot)) {
            if (this.orderedSlots.containsKey(slot)) {
                List<Comparable<Comparable<?>>> list = (List) this.orderedSlots.get(slot);
                list.add((Comparable) content);
                Collections.sort(list);
                int index = 0;

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) == content) {
                        index = i;
                    }
                }

                InsertPanel insertPanel = (InsertPanel) this.hasWidgetSlots.get(slot);
                insertPanel.insert(content.asWidget(), index);
            } else {
                ((HasWidgets) this.hasWidgetSlots.get(slot)).add(content.asWidget());
            }
        }

    }

    public void removeFromSlot(Object slot, IsWidget content) {
        if (this.oneWidgetSlots.containsKey(slot)) {
            if (((HasOneWidget) this.oneWidgetSlots.get(slot)).getWidget() == content.asWidget()) {
                ((HasOneWidget) this.oneWidgetSlots.get(slot)).setWidget((Widget) null);
            }
        } else if (this.hasWidgetSlots.containsKey(slot)) {
            ((HasWidgets) this.hasWidgetSlots.get(slot)).remove(content.asWidget());
            if (this.orderedSlots.containsKey(slot)) {
                ((List) this.orderedSlots.get(slot)).remove(content);
            }
        }

    }

    public void setInSlot(Object slot, IsWidget content) {
        setInSlot(slot, content, false);
    }

    public void setInSlot(Object slot, IsWidget content, boolean allowClear) {
        if (this.oneWidgetSlots.containsKey(slot)) {
            ((HasOneWidget) this.oneWidgetSlots.get(slot)).setWidget(content);
        } else if (this.hasWidgetSlots.containsKey(slot)) {
            if (allowClear) {
                ((HasWidgets) this.hasWidgetSlots.get(slot)).clear();
            }
            if (content != null) {
                ((HasWidgets) this.hasWidgetSlots.get(slot)).add(content.asWidget());
            }

            if (this.orderedSlots.containsKey(slot)) {
                if (allowClear) {
                    ((List) this.orderedSlots.get(slot)).clear();
                }
                if (content != null) {
                    ((List) this.orderedSlots.get(slot)).add((Comparable) content);
                }
            }
        }
    }

    public Widget asWidget() {
        if (this.widget == null) {
            throw new NullPointerException("widget cannot be null, you should call ViewImpl.initWidget() before.");
        } else {
            return this.widget;
        }
    }

    protected void bindSlot(IsSingleSlot<?> slot, Object container) {
        this.internalBindSlot(slot, container);
    }

    protected void bindSlot(Slot<?> slot, HasWidgets container) {
        this.internalBindSlot(slot, container);
    }

    protected <T extends HasWidgets & InsertPanel> void bindSlot(OrderedSlot<?> slot, T container) {
        this.orderedSlots.put(slot, new ArrayList());
        this.hasWidgetSlots.put(slot, container);
    }

    protected void initWidget(IsWidget widget) {
        if (this.widget != null) {
            throw new IllegalStateException("ViewImpl.initWidget() may only be called once.");
        } else if (widget == null) {
            throw new NullPointerException("widget cannot be null");
        } else {
            this.widget = widget.asWidget();
            this.asWidget().addAttachHandler(new Handler() {
                public void onAttachOrDetach(AttachEvent event) {
                    if (event.isAttached()) {
                        ViewImpl.this.onAttach();
                    } else {
                        ViewImpl.this.onDetach();
                    }

                }
            });
        }
    }

    protected void onAttach() {
    }

    protected void onDetach() {
    }

    private void internalBindSlot(Object slot, Object container) {
        if (container instanceof HasOneWidget) {
            this.oneWidgetSlots.put(slot, (HasOneWidget) container);
        } else {
            if (!(container instanceof HasWidgets)) {
                throw new IllegalArgumentException("Containers must implement either HasOneWidget or HasWidgets.");
            }

            this.hasWidgetSlots.put(slot, (HasWidgets) container);
        }

    }
}
