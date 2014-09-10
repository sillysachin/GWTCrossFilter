package com.insanity.sample.server.flightservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.insanity.sample.client.data.flight.FlightDelayData;
import com.insanity.sample.client.data.metric.MetricData;
import com.insanity.sample.client.flightservice.JSONFlightService;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings( "serial" )
public class JSONFlightServiceImpl extends RemoteServiceServlet implements JSONFlightService
{
	@Override
	public ArrayList<FlightDelayData> getFlighDelayData()
	{
		BufferedReader reader = null;
		ServletContext cntxt = this.getServletContext();
		String fName = "/data/flights-light.csv";
		InputStream ins = cntxt.getResourceAsStream( fName );
		ArrayList<FlightDelayData> flightDataItems = new ArrayList<FlightDelayData>();
		try
		{
			if ( ins != null )
			{
				InputStreamReader isr = new InputStreamReader( ins );
				reader = new BufferedReader( isr );
				int n = 0;
				String sCurrentLine = "";
				while ( ( sCurrentLine = reader.readLine() ) != null )
				{
					String[] tokens = sCurrentLine.split( "," );
					FlightDelayData flightDelayDataItem = new FlightDelayData();
					flightDelayDataItem.setDate( new Date( Long.parseLong( tokens[0] ) ) );
					flightDelayDataItem.setDelay( Integer.parseInt( tokens[1] ) );
					flightDelayDataItem.setDistance( Integer.parseInt( tokens[2] ) );
					flightDelayDataItem.setOrigin( tokens[3] );
					flightDelayDataItem.setDestination( tokens[4] );
					flightDataItems.add( flightDelayDataItem );
				}
			}
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if ( reader != null )
					reader.close();
			}
			catch ( IOException ex )
			{
				ex.printStackTrace();
			}
		}
		return flightDataItems;
	}

	@Override
	public ArrayList<MetricData> getMetricData()
	{
		BufferedReader reader = null;
		ServletContext cntxt = this.getServletContext();
		String fName = "/data/metric-data.csv";
		InputStream ins = cntxt.getResourceAsStream( fName );
		ArrayList<MetricData> metricDataItems = new ArrayList<MetricData>();
		try
		{
			if ( ins != null )
			{
				InputStreamReader isr = new InputStreamReader( ins );
				reader = new BufferedReader( isr );
				String sCurrentLine = "";
				while ( ( sCurrentLine = reader.readLine() ) != null )
				{
					String[] tokens = sCurrentLine.split( "," );
					MetricData metricDataItem = new MetricData();
					metricDataItem.setId(  Integer.parseInt( tokens[0] ) );
					metricDataItem.setMetricDate( new Date( Long.parseLong( tokens[1] ) ) );
					metricDataItem.setGrossRevenue( Double.parseDouble( tokens[2] ) );
					metricDataItem.setRecoverable( Double.parseDouble( tokens[3] ) );
					metricDataItem.setRecovered( Double.parseDouble(tokens[4]) );
					metricDataItem.setUnrecoverable( Double.parseDouble(tokens[5]) );
					metricDataItem.setFalsePositive( Double.parseDouble(tokens[6]) );
					metricDataItem.setOverallPotentialLeakage( Double.parseDouble(tokens[7]) );
					metricDataItems.add( metricDataItem );
				}
			}
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if ( reader != null )
					reader.close();
			}
			catch ( IOException ex )
			{
				ex.printStackTrace();
			}
		}
		return metricDataItems;	}
}