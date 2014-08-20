package com.insanity.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class Bootstrap2DashboardUiBinder extends Composite {
    interface ExampleUiBinderUiBinder extends UiBinder<HTMLPanel, Bootstrap2DashboardUiBinder> {
    }

    private static ExampleUiBinderUiBinder ourUiBinder = GWT.create(ExampleUiBinderUiBinder.class);

    public Bootstrap2DashboardUiBinder() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}