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
package com.github.gwtmaterialdesign.client.application.fadethrough.source;

import com.github.gwtmaterialdesign.client.application.fadethrough.FadeThroughPresenter;
import com.github.gwtmaterialdesign.client.generator.DataGenerator;
import com.github.gwtmaterialdesign.client.generator.user.User;
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

public class SourcePresenter extends Presenter<SourcePresenter.MyView, SourcePresenter.MyProxy> {
    interface MyView extends View {
        void buildUsers(List<User> users);
    }

    @ProxyStandard
    @NameToken(NameTokens.USERS)
    interface MyProxy extends ProxyPlace<SourcePresenter> {
    }

    @Inject
    public SourcePresenter(
        EventBus eventBus,
        MyView view,
        MyProxy proxy) {
        super(eventBus, view, proxy, FadeThroughPresenter.SLOT_MAIN);
    }

    @Override
    protected void onBind() {
        super.onBind();
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        asWidget().setVisible(false);
        MaterialFadeThrough.getInstance().enter(asWidget());

        // Build Users
        getView().buildUsers(new DataGenerator().generateUsers(10));
    }

    @Override
    protected void onHide() {
        super.onHide();

        MaterialFadeThrough.getInstance().exit(asWidget(), () -> asWidget().removeFromParent());
    }
}
