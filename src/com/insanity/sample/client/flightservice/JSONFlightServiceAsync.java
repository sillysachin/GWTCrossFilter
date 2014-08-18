package com.insanity.sample.client.flightservice;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.insanity.sample.client.data.flight.FlightDelayData;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface JSONFlightServiceAsync
{
	void getFlighDelayData( AsyncCallback<ArrayList<FlightDelayData>> asyncCallback ) throws IllegalArgumentException;
}