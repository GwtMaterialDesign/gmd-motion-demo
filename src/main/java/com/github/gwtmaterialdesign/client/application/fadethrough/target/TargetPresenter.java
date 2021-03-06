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
package com.github.gwtmaterialdesign.client.application.fadethrough.target;

import com.github.gwtmaterialdesign.client.application.fadethrough.FadeThroughPresenter;
import com.github.gwtmaterialdesign.client.generator.DataGenerator;
import com.github.gwtmaterialdesign.client.generator.product.Product;
import com.github.gwtmaterialdesign.client.place.NameTokens;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import gwt.material.design.motion.client.pattern.fadethrough.MaterialFadeThrough;

import java.util.List;

public class TargetPresenter extends Presenter<TargetPresenter.MyView, TargetPresenter.MyProxy> {

    interface MyView extends View {
        void buildProducts(List<Product> products);
    }

    @ProxyStandard
    @NameToken(NameTokens.PRODUCTS)
    interface MyProxy extends ProxyPlace<TargetPresenter> {
    }

    @Inject
    public TargetPresenter(
        EventBus eventBus,
        MyView view,
        MyProxy proxy) {
        super(eventBus, view, proxy, FadeThroughPresenter.SLOT_MAIN);
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        asWidget().setVisible(false);
        MaterialFadeThrough.getInstance().enter(asWidget());
        getView().buildProducts(new DataGenerator().generateProducts(5));
    }

    @Override
    protected void onHide() {
        super.onHide();

        MaterialFadeThrough.getInstance().exit(asWidget(), () -> asWidget().removeFromParent());
    }
}
