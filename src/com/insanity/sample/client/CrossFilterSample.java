package com.insanity.sample.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.google.gwt.core.client.Duration;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ValueListBox;
import com.insanity.sample.client.data.flight.FlightDelayData;
import com.insanity.sample.client.flightservice.JSONFlightService;
import com.insanity.sample.client.flightservice.JSONFlightServiceAsync;
import com.square.crossfilter.client.Crossfilter;
import com.square.crossfilter.client.CrossfilterUtil;
import com.square.crossfilter.client.Dimension;
import com.square.crossfilter.client.Dimension.DateReducer;
import com.square.crossfilter.client.Dimension.DoubleReducer;
import com.square.crossfilter.client.Dimension.IntReducer;
import com.square.crossfilter.client.Dimension.StringReducer;
import com.square.crossfilter.client.Group;
import com.square.crossfilter.client.SingleGroup;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CrossFilterSample implements EntryPoint {
	private final JSONFlightServiceAsync jsonFlightService = GWT
			.create(JSONFlightService.class);

	public void onModuleLoad() {
		final double start = Duration.currentTimeMillis();
		jsonFlightService
				.getFlighDelayData(new AsyncCallback<ArrayList<FlightDelayData>>() {
					public void onSuccess(ArrayList<FlightDelayData> data) {
						double end = Duration.currentTimeMillis();
						GWT.log("Done RPC in " + (end - start) / 1000
								+ " Seconds");
						Crossfilter<FlightDelayData> flights = initCrossFilter(data);
						ArrayList<Dimension<FlightDelayData, ? extends Object>> flightDimensions = initDimensions(flights);
						initWidgets(flightDimensions);
					}

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Oops....", caught);
					}
				});
	}

	protected void initWidgets(
			ArrayList<Dimension<FlightDelayData, ? extends Object>> flightDimensions) {
	}

	private Crossfilter<FlightDelayData> initCrossFilter(
			ArrayList<FlightDelayData> data) {
		final double startCrossfilter = Duration.currentTimeMillis();
		final Crossfilter<FlightDelayData> flights = Crossfilter.create(data);
		final double endCrossfilter = Duration.currentTimeMillis();
		GWT.log("Done Crossfilter in " + (endCrossfilter - startCrossfilter)
				/ 1000 + " Seconds");
		GWT.log("Crossfilter Size " + flights.size());
		final double startAddCrossfilter = Duration.currentTimeMillis();
		FlightDelayData flightDelayData = new FlightDelayData();
		flightDelayData.setDate(new Date(
				data.get(0).getDate().getTime() + 36000));
		flightDelayData.setDelay(data.get(0).getDelay() + 1);
		flightDelayData.setDestination(data.get(0).getDestination());
		flightDelayData.setDistance(data.get(0).getDistance());
		flightDelayData.setOrigin(data.get(0).getOrigin());
		flights.add(flightDelayData);
		final double endAddCrossfilter = Duration.currentTimeMillis();
		GWT.log("Done AddCrossfilter in "
				+ (endAddCrossfilter - startAddCrossfilter) / 1000 + " Seconds");
		GWT.log("Crossfilter Size " + flights.size());
		return flights;
	}

	protected ArrayList<Dimension<FlightDelayData, ? extends Object>> initDimensions(
			Crossfilter<FlightDelayData> flights) {
		final double startAddDimensions = Duration.currentTimeMillis();

		final Dimension<FlightDelayData, Date> date = flights
				.dimension(new DateReducer<FlightDelayData>() {
					public Date getValue(FlightDelayData object) {
						return object.getDate();
					}
				});
		final Dimension<FlightDelayData, Double> hour = flights
				.dimension(new DoubleReducer<FlightDelayData>() {
					public double getValue(FlightDelayData object) {
						return object.getDate().getHours()
								+ object.getDate().getMinutes() / 60;
					}
				});
		Dimension<FlightDelayData, Integer> delay = flights
				.dimension(new IntReducer<FlightDelayData>() {
					public int getValue(FlightDelayData object) {
						return Math.max(-60, Math.min(149, object.getDelay()));
					}
				});
		Dimension<FlightDelayData, Integer> distance = flights
				.dimension(new IntReducer<FlightDelayData>() {
					public int getValue(FlightDelayData object) {
						return Math.min(1999, object.getDistance());
					}
				});
		final Dimension<FlightDelayData, String> origin = flights
				.dimension(new StringReducer<FlightDelayData>() {
					public String getValue(FlightDelayData object) {
						return object.getOrigin();
					}
				});
		final Dimension<FlightDelayData, String> destination = flights
				.dimension(new StringReducer<FlightDelayData>() {
					public String getValue(FlightDelayData object) {
						return object.getDestination();
					}
				});
		final double endAddDimensions = Duration.currentTimeMillis();
		GWT.log("Done AddDimensions in "
				+ (endAddDimensions - startAddDimensions) / 1000 + " Seconds");

		ArrayList<Dimension<FlightDelayData, ? extends Object>> dimensions = new ArrayList<>();
		dimensions.add(delay);
		dimensions.add(date);
		dimensions.add(destination);
		dimensions.add(distance);
		dimensions.add(origin);

		final double startAddGroups = Duration.currentTimeMillis();

		SingleGroup<FlightDelayData, Object> all = flights.groupAll();
		Group<FlightDelayData, Date> dates = date.group(CrossfilterUtil
				.createGroupingDays());
		Group<FlightDelayData, Double> hours = hour.group(CrossfilterUtil
				.createGroupingToFloor());
		Group<FlightDelayData, Integer> delays = delay.group(CrossfilterUtil
				.createGroupingToFloor(10));
		Group<FlightDelayData, Integer> distances = distance
				.group(CrossfilterUtil.createGroupingToFloor(50));

		final double endAddGroups = Duration.currentTimeMillis();
		GWT.log("Done AddGroups in " + (startAddGroups - endAddGroups) / 1000
				+ " Seconds");

		ValueListBox<FlightDelayData> originwidget = new ValueListBox<FlightDelayData>(
				new Renderer<FlightDelayData>() {
					public String render(FlightDelayData object) {
						if (object != null) {
							return object.getOrigin();
						} else
							return "";
					}

					@Override
					public void render(FlightDelayData object,
							Appendable appendable) throws IOException {
						appendable.append(render(object));
					};
				});
		Collection<FlightDelayData> top5origin = origin.top(5);
		originwidget.setAcceptableValues(top5origin);
		HorizontalPanel panel = new HorizontalPanel();
		panel.setWidth("75%");
		panel.add(originwidget);

		RootLayoutPanel.get().add(new DashboardUiBinder());
		return dimensions;
	}

}