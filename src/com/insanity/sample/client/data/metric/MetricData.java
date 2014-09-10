package com.insanity.sample.client.data.metric;

import java.io.Serializable;
import java.util.Date;

public class MetricData implements Serializable
{
	private static final long serialVersionUID = -4666578428556417095L;

	private Integer id;

	private Date metricDate;

	private Double grossRevenue;

	private Double overallPotentialLeakage;

	private Double recoverable;

	private Double unrecoverable;

	private Double recovered;

	private Double falsePositive;

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public Date getMetricDate()
	{
		return metricDate;
	}

	public void setMetricDate( Date metricDate )
	{
		this.metricDate = metricDate;
	}

	public Double getGrossRevenue()
	{
		return grossRevenue;
	}

	public void setGrossRevenue( Double grossRevenue )
	{
		this.grossRevenue = grossRevenue;
	}

	public Double getOverallPotentialLeakage()
	{
		return overallPotentialLeakage;
	}

	public void setOverallPotentialLeakage( Double overallPotentialLeakage )
	{
		this.overallPotentialLeakage = overallPotentialLeakage;
	}

	public Double getRecoverable()
	{
		return recoverable;
	}

	public void setRecoverable( Double recoverable )
	{
		this.recoverable = recoverable;
	}

	public Double getUnrecoverable()
	{
		return unrecoverable;
	}

	public void setUnrecoverable( Double unrecoverable )
	{
		this.unrecoverable = unrecoverable;
	}

	public Double getRecovered()
	{
		return recovered;
	}

	public void setRecovered( Double recovered )
	{
		this.recovered = recovered;
	}

	public Double getFalsePositive()
	{
		return falsePositive;
	}

	public void setFalsePositive( Double falsePositive )
	{
		this.falsePositive = falsePositive;
	}
}