/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.github.gwtmaterialdesign.client.application;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialContainer;
import gwt.material.design.client.ui.MaterialNavBar;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.ui.animate.debugger.AnimationGlobalConfig;
import gwt.material.design.client.ui.animate.debugger.AnimationSpeed;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
    interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    @UiField
    MaterialContainer container;

    @UiField
    MaterialButton btnAdd;

    @Inject
    ApplicationView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        bindSlot(ApplicationPresenter.SLOT_MAIN, container);
        AnimationGlobalConfig.SPEED = AnimationSpeed.NORMAL;
    }

    @Override
    protected void onAttach() {
        super.onAttach();
    }

    @UiHandler("btnAdd")
    void onAdd(ClickEvent e) {
        MaterialToast.fireToast("I love GMD");
    }
}
