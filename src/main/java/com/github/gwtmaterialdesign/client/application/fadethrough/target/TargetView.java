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

import com.github.gwtmaterialdesign.client.generator.product.Product;
import com.github.gwtmaterialdesign.client.widget.ProductCollectionItem;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialRow;

import javax.inject.Inject;
import java.util.List;

public class TargetView extends ViewImpl implements TargetPresenter.MyView {

    interface Binder extends UiBinder<Widget, TargetView> {
    }

    @UiField
    MaterialCollection productCollection;

    @Inject
    TargetView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void onAttach() {
        super.onAttach();
    }

    @Override
    public void buildProducts(List<Product> products) {
        productCollection.clear();
        for (Product product : products) {
            productCollection.add(new ProductCollectionItem(product));
        }
    }
}
