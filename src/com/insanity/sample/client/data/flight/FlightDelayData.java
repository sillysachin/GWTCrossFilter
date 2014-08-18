package com.insanity.sample.client.data.flight;

import java.io.Serializable;
import java.util.Date;

public class FlightDelayData implements Serializable
{
	private static final long serialVersionUID = 4596966375228539973L;

	private int delay;

	private int distance;

	private String origin;

	private String destination;

	private Date date;

	public int getDelay()
	{
		return delay;
	}

	public void setDelay( int delay )
	{
		this.delay = delay;
	}

	public int getDistance()
	{
		return distance;
	}

	public void setDistance( int distance )
	{
		this.distance = distance;
	}

	public String getOrigin()
	{
		return origin;
	}

	public void setOrigin( String origin )
	{
		this.origin = origin;
	}

	public String getDestination()
	{
		return destination;
	}

	public void setDestination( String destination )
	{
		this.destination = destination;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate( Date date )
	{
		this.date = date;
	}

	@Override
	public String toString()
	{
		return "FlightDelayData [delay=" + delay + ", distance=" + distance + ", origin=" + origin + ", destination=" + destination + ", date=" + date + "]";
	}
}