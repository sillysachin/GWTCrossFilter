package com.insanity.sample.client.flightservice;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.insanity.sample.client.data.flight.FlightDelayData;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath( "jsonFlightService" )
public interface JSONFlightService extends RemoteService
{
	ArrayList<FlightDelayData> getFlighDelayData();
}
