package com.gxuwz.fireControl.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndRandom {
	public String dateRandom() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int r = (int) (Math.random() * 900) + 100;
		return dateFormat.format(new Date()) + r;
	}

	public Timestamp Time() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		Timestamp ts = Timestamp.valueOf(time);
		return ts;
	}

	public static void main(String[] args) {
		// System.out.println(dateRandom());
	}
}
