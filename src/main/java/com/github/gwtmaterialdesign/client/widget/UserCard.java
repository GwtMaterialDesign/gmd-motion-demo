package com.github.gwtmaterialdesign.client.widget;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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


import com.github.gwtmaterialdesign.client.generator.user.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialLabel;

public class UserCard extends Composite {

    private static DashboardCardUiBinder uiBinder = GWT.create(DashboardCardUiBinder.class);
    private final User user;

    @UiField
    MaterialImage image;

    @UiField
    MaterialLabel title, description;

    interface DashboardCardUiBinder extends UiBinder<MaterialColumn, UserCard> {
    }

    public UserCard(User user) {
        initWidget(uiBinder.createAndBindUi(this));

        this.user = user;
    }

    @Override
    protected void onAttach() {
        super.onAttach();

        image.setUrl(user.getImage());
        title.setText(user.getName());
        description.setText(user.getEmail());
    }
}
