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
package com.github.gwtmaterialdesign.client.application.fadethrough;

import com.github.gwtmaterialdesign.client.place.NameTokens;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import gwt.material.design.client.ui.MaterialContainer;

public class FadeThroughView extends ViewImpl implements FadeThroughPresenter.MyView {
    interface Binder extends UiBinder<Widget, FadeThroughView> {
    }

    @UiField
    MaterialContainer container;

    private PlaceManager placeManager;

    @Inject
    FadeThroughView(Binder uiBinder,
                    PlaceManager placeManager) {
        initWidget(uiBinder.createAndBindUi(this));
        bindSlot(FadeThroughPresenter.SLOT_MAIN, container);

        this.placeManager = placeManager;
    }

    @UiHandler("users")
    void users(ClickEvent event) {
        PlaceRequest placeRequest = new PlaceRequest.Builder()
            .nameToken(NameTokens.USERS)
            .build();

        placeManager.revealPlace(placeRequest);
    }

    @UiHandler("products")
    void products(ClickEvent event) {
        PlaceRequest placeRequest = new PlaceRequest.Builder()
            .nameToken(NameTokens.PRODUCTS)
            .build();

        placeManager.revealPlace(placeRequest);
    }
}
