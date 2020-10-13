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

import com.github.gwtmaterialdesign.client.application.fadethrough.source.SourceModule;
import com.github.gwtmaterialdesign.client.application.fadethrough.target.TargetModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class
FadeThroughModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new SourceModule());
        install(new TargetModule());

        bindPresenter(FadeThroughPresenter.class, FadeThroughPresenter.MyView.class, FadeThroughView.class,
                FadeThroughPresenter.MyProxy.class);
    }
}