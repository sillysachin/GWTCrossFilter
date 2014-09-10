package com.insanity.sample.client.flightservice;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.insanity.sample.client.data.flight.FlightDelayData;
import com.insanity.sample.client.data.metric.MetricData;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface JSONFlightServiceAsync
{
	void getFlighDelayData( AsyncCallback<ArrayList<FlightDelayData>> asyncCallback ) throws IllegalArgumentException;
	void getMetricData( AsyncCallback<ArrayList<MetricData>> asyncCallback ) throws IllegalArgumentException;
}