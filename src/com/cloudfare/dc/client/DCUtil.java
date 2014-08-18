package com.cloudfare.dc.client;

import com.google.gwt.core.client.JavaScriptObject;

public class DCUtil
{
	public static final native JavaScriptObject floor() /*-{
	return $wnd.dc.round.floor;
	}-*/;

	public static final native JavaScriptObject round() /*-{
	return $wnd.dc.round.round;
	}-*/;

	public static final native JavaScriptObject unitIntegers() /*-{
	return $wnd.dc.units.integers;
	}-*/;

	public static final native JavaScriptObject unitPrecision( double precision ) /*-{
	return $wnd.dc.units.fp.precision(precision);
	}-*/;
}