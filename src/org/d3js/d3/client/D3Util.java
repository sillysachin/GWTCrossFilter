package org.d3js.d3.client;

import com.google.gwt.core.client.JavaScriptObject;

public class D3Util {

	public static final native JavaScriptObject roundToDays() /*-{
		return function(v) {
			return $wnd.d3.time.day(v);
		};
	}-*/;

	public static final native JavaScriptObject roundToWeeks() /*-{
		return function(v) {
			return $wnd.d3.time.week(v);
		};
	}-*/;

	public static final native JavaScriptObject timeMonths() /*-{
		return $wnd.d3.time.months;
	}-*/;

	public static final native JavaScriptObject timeDays() /*-{
		return $wnd.d3.time.days;
	}-*/;

	public static final native JavaScriptObject roundDays() /*-{
		return $wnd.d3.time.days.round;
	}-*/;

	public static final JavaScriptObject timeDomain(long start, long end) {
		return timeDomain((double) start, (double) end);
	}

	private static final native JavaScriptObject timeDomain(double start,
			double end) /*-{
		return $wnd.d3.time.scale().domain([ new Date(start), new Date(end) ]);
	}-*/;

	public static final native JavaScriptObject linearDomain(double start,
			double end) /*-{
		return $wnd.d3.scale.linear().domain([ start, end ]).nice(5);
	}-*/;

	public static final native JavaScriptObject linearDomain(int start, int end) /*-{
		return $wnd.d3.scale.linear().domain([ start, end ]).nice(5);
	}-*/;
}