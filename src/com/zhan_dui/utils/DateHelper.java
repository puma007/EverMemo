package com.zhan_dui.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;

import com.zhan_dui.evermemo.R;

public class DateHelper {

	public static final long ONE_DAY_TIMESTAMP = 86400000;

	public static String getReadableDate(Context context,
			SimpleDateFormat dateFormat, long timemillisecond) {
		long span = System.currentTimeMillis() - timemillisecond;
		long timeSpan = span / ONE_DAY_TIMESTAMP;
		if (timeSpan == 1) {
			return context.getString(R.string.yesterday);
		} else {
			return dateFormat.format(new Date(timemillisecond));
		}
	}
}