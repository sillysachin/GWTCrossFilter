package com.insanity.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Bootstrap3DashboardUiBinder extends Composite {
    interface ExampleUiBinderUiBinder extends UiBinder<Widget, Bootstrap3DashboardUiBinder> {
    }

    private static ExampleUiBinderUiBinder ourUiBinder = GWT.create(ExampleUiBinderUiBinder.class);

    public Bootstrap3DashboardUiBinder() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}