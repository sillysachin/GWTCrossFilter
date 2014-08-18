package com.square.crossfilter.client;

import java.util.Date;

import org.d3js.d3.client.D3Util;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsDate;
import com.square.crossfilter.client.Dimension.Grouping;

public class CrossfilterUtil {

	public static JsDate toJs(Date date) {
		return JsDate.create((double) date.getTime());
	}

	public static Date toGWT(JsDate jsDate) {
		return new Date((long) jsDate.getTime());
	}

	public static Grouping<Double> createGroupingMultipleOf(double m) {
		return new GroupingFunction<Double>(roundToMultiple(m));
	}

	public static Grouping<Date> createGroupingDays() {
		return new GroupingFunction<Date>(D3Util.roundToDays());
	}

	public static Grouping<Date> createGroupingWeeks() {
		return new GroupingFunction<Date>(D3Util.roundToWeeks());
	}

	public static final native JavaScriptObject roundToMultiple(double m) /*-{
		return function(v) {
			return Math.round(v / m) * m;
		};
	}-*/;

	public static Grouping<Integer> createGroupingToFloor(int m) {
		return new GroupingFunction<Integer>(toFloor(m));
	}

	public static Grouping<Double> createGroupingToFloor() {
		return new GroupingFunction<Double>(toFloor());
	}

	public static final native JavaScriptObject toFloor(int m) /*-{
		return function(v) {
			return Math.floor(v / m) * m;
		};
	}-*/;

	public static final native JavaScriptObject toFloor() /*-{
		return function(v) {
			return Math.floor;
		};
	}-*/;

	private static class GroupingFunction<K> extends Grouping<K> {
		private final JavaScriptObject function;

		public GroupingFunction(JavaScriptObject function) {
			this.function = function;
		}

		@Override
		protected JavaScriptObject getGroupFunction() {
			return function;
		}
	}

}
