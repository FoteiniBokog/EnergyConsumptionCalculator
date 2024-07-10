package timeaggregation;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dataload.ILoader;
import dataload.LoaderFactory;
import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.Result;

public class Aggregator implements IAggregator{
	
	private String TimeUnitType;
	

	
	public Aggregator(String timeUnitType) {
		super();
		TimeUnitType = timeUnitType;
	}

	@Override
	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggFunction,String description){
		IResult myResult=new Result();
		if (TimeUnitType.equals("dayofweek")) {
			System.out.println("hello");
			for (MeasurementRecord mr : inputMeasurements) {
				//System.out.println("hello2");
				myResult.add(mr.getDayOfWeek(), mr);
				//System.out.println("hello3");
			}
		}
		 
		else if(TimeUnitType.equals("season")){
			//System.out.println("hello1");
			for (MeasurementRecord mr : inputMeasurements) {
			myResult.add(mr.getSeason(), mr);
			}
		}
		else if(TimeUnitType.equals("periodofday")){
			for (MeasurementRecord mr : inputMeasurements) {
				myResult.add(mr.getTimeOfDay(), mr);
				}
		}
		else if(TimeUnitType.equals("month")){
			for (MeasurementRecord mr : inputMeasurements) {
				myResult.add(String.valueOf(mr.getMonth()), mr);
				}
		}
		for (Map.Entry<String, ArrayList<MeasurementRecord>> m: myResult.getDetailedResults().entrySet()) {
			double sumKitchen=0;
			double sumLaundry=0;
			double sumAC=0;
			double sumKitchen2=0;
			double sumLaundry2=0;
			double sumAC2=0;
			int size=m.getValue().size();
			
			for (MeasurementRecord mr: m.getValue()) {
				if(aggFunction.equals("sum")) {
					sumKitchen+=mr.getSub_metering_kitchen();
					sumLaundry+=mr.getSub_metering_laundry();
					sumAC+=mr.getSub_metering_AC();
				}
				else if(aggFunction.equals("avg")){
					sumKitchen2+=mr.getSub_metering_kitchen();
					sumLaundry2+=mr.getSub_metering_laundry();
					sumAC2+=mr.getSub_metering_AC();
				}
			}
			if(aggFunction.equals("sum")) {
				myResult=new Result(m.getKey(), sumKitchen, sumLaundry, sumAC, myResult.getDetailedResults());
			}
			else if(aggFunction.equals("avg")) {
				myResult=new Result(m.getKey(), sumKitchen2/(double) size, sumLaundry2/(double) size, sumAC2/(double) size, myResult.getDetailedResults());
			}
		}
			
		
		return myResult;
		
	}
	
	

	@Override
	public String getTimeUnitType() {
		return this.TimeUnitType;
	}
	public static void main(String Args[]) {
		ArrayList<MeasurementRecord>marika=new ArrayList<MeasurementRecord>();
		LoaderFactory loader=new LoaderFactory();
		ILoader<MeasurementRecord> myLoader=loader.createLoader("Loader");
		myLoader.load("File", "\t", false, 9, marika);
		Aggregator myAggregator = new Aggregator("month");
		IResult myResult=myAggregator.aggregateByTimeUnit(marika, "avg", "a des");
		for (Map.Entry<String, Double> m: myResult.getAggregateMeterLaundry().entrySet()) {
			System.out.println(m.getKey()+": "+m.getValue());
			
		}
		
	}
}
