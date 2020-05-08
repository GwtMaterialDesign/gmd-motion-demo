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
package com.github.gwtmaterialdesign.client.application.home.dashboard;

import com.github.gwtmaterialdesign.client.application.ApplicationPresenter;
import com.github.gwtmaterialdesign.client.application.home.HomePresenter;
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
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;
import gwt.material.design.motion.client.pattern.fadethrough.MaterialFadeThrough;

import java.util.List;

public class DashboardPresenter extends Presenter<DashboardPresenter.MyView, DashboardPresenter.MyProxy> {

    interface MyView extends View {
    }

    @ProxyStandard
    @NameToken(NameTokens.DASHBOARD)
    interface MyProxy extends ProxyPlace<DashboardPresenter> {
    }

    @Inject
    public DashboardPresenter(
        EventBus eventBus,
        MyView view,
        MyProxy proxy) {
        super(eventBus, view, proxy, HomePresenter.SLOT_MAIN);
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        new MaterialAnimation()
            .transition(Transition.SHARED_AXIS_Y_FORWARD_IN)
            .animate(asWidget());
    }

    @Override
    protected void onHide() {
        super.onHide();
    }
}
