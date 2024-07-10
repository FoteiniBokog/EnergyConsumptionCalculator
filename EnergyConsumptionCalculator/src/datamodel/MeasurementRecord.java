package datamodel;

import java.util.ArrayList;
import java.util.Formatter;
import dataload.LoaderFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MeasurementRecord{
	
	private Double sub_metering_kitchen;
	private Double sub_metering_laundry;
	private Double sub_metering_AC;
	
	private String Description;
	private DateTimeFormatter formatter;
	private LocalDateTime localDateTime;
	
	private String season;
	private int month;
	private String dayOfWeek;
	private String timeOfDay;
	private int time;
	
	
	public MeasurementRecord(String Date,String Time, Double sub_metering_kitchen,Double sub_metering_laundry,Double sub_metering_AC) {
		formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		localDateTime=LocalDateTime.parse(Date+" "+Time, this.formatter);
		
		this.sub_metering_AC=sub_metering_AC;
		this.sub_metering_kitchen=sub_metering_kitchen;
		this.sub_metering_laundry=sub_metering_laundry;
		
		this.month=localDateTime.getMonthValue();
		this.time=localDateTime.getHour();
		this.dayOfWeek=localDateTime.getDayOfWeek().toString();
		
	}
	
	
	public String getDescription(){
		return Description;
	}


	public Double getSub_metering_kitchen() {
		return sub_metering_kitchen;
	}


	public Double getSub_metering_laundry() {
		return sub_metering_laundry;
	}


	public Double getSub_metering_AC() {
		return sub_metering_AC;
	}


	public String getSeason() {
		//TODO seasson...
		if (this.month>=3 && this.month<=5) {
			return "WIINTER";
		}
		return "";
	}


	public int getMonth() {
		return month;
	}


	public String getDayOfWeek() {
		return dayOfWeek;
	}


	public String getTimeOfDay() {
		//TODO ...
		return timeOfDay;
	}

	
 
}

