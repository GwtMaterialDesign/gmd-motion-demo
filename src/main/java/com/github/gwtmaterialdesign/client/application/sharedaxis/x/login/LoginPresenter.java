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
package com.github.gwtmaterialdesign.client.application.sharedaxis.x.login;

import com.github.gwtmaterialdesign.client.application.sharedaxis.x.SharedAxisXPresenter;
import com.github.gwtmaterialdesign.client.generator.user.User;
import com.github.gwtmaterialdesign.client.generator.user.UserGenerator;
import com.github.gwtmaterialdesign.client.place.NameTokens;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import gwt.material.design.motion.client.pattern.fadethrough.MaterialFadeThrough;
import gwt.material.design.motion.client.pattern.sharedaxis.MaterialSharedAxis;

public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy> {
    interface MyView extends View {
        void setUser(User user);
    }

    @ProxyStandard
    @NameToken(NameTokens.LOGIN)
    interface MyProxy extends ProxyPlace<LoginPresenter> {
    }

    @Inject
    public LoginPresenter(
        EventBus eventBus,
        MyView view,
        MyProxy proxy) {
        super(eventBus, view, proxy, SharedAxisXPresenter.SLOT_MAIN);
    }

    @Override
    protected void onBind() {
        super.onBind();
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        asWidget().setVisible(false);
        MaterialSharedAxis.getInstance().enter(asWidget());
        getView().setUser(UserGenerator.generate());
    }

    @Override
    protected void onHide() {
        super.onHide();

        MaterialSharedAxis.getInstance().exit(asWidget(), () -> asWidget().removeFromParent());
    }
}
