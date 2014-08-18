package com.insanity.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class DashboardUiBinder extends Composite {
    interface ExampleUiBinderUiBinder extends UiBinder<HTMLPanel, DashboardUiBinder> {
    }

    private static ExampleUiBinderUiBinder ourUiBinder = GWT.create(ExampleUiBinderUiBinder.class);

    public DashboardUiBinder() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}