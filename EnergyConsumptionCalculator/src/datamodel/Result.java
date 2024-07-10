package datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Result implements IResult {
	
	private HashMap<String, Double> aggregateMeterKitchen;
	private HashMap<String, Double> aggregateMeterLaundry;
	private HashMap<String, Double> aggregateMeterAc;
	private HashMap<String, ArrayList<MeasurementRecord>> detailedResults;
	private String aggregateFuction;
	
	
	private String description;
	
	public Result(String timeUnit, double kitchenMeter,double laundryMeter,double ACMeter, HashMap<String, ArrayList<MeasurementRecord>> detailedResults) {
		this.aggregateMeterKitchen=new HashMap<String, Double> ();
		this.aggregateMeterLaundry=new HashMap<String, Double> ();
		this.aggregateMeterAc=new HashMap<String, Double> ();
		this.aggregateMeterKitchen.put(timeUnit, kitchenMeter);
		this.aggregateMeterLaundry.put(timeUnit, laundryMeter);
		this.aggregateMeterAc.put(timeUnit, ACMeter);
		this.detailedResults=detailedResults;
		
	}
	public Result() {
		this.aggregateMeterKitchen=new HashMap<String, Double> ();
		this.aggregateMeterLaundry=new HashMap<String, Double> ();
		this.aggregateMeterAc=new HashMap<String, Double> ();
		this.detailedResults=new HashMap<>();
	}

	@Override
	public int add(String timeUnit, MeasurementRecord record){
		if (this.detailedResults.containsKey(timeUnit)) {
			ArrayList<MeasurementRecord> measurementRecord= this.detailedResults.get(timeUnit);
			measurementRecord.add(record);
			this.detailedResults.put(timeUnit, measurementRecord);
		}
		else {
			ArrayList<MeasurementRecord> measurementRecord= new ArrayList<>();
			measurementRecord.add(record);
			this.detailedResults.put(timeUnit, measurementRecord);
		}
		return this.detailedResults.size();
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public HashMap<String, ArrayList<MeasurementRecord>> getDetailedResults() {
		return this.detailedResults;
	}

	@Override
	public HashMap<String, Double> getAggregateMeterKitchen() {
		
		return this.aggregateMeterKitchen;
	}

	@Override
	public HashMap<String, Double> getAggregateMeterLaundry() {
		return this.aggregateMeterLaundry;
	}

	@Override
	public HashMap<String, Double> getAggregateMeterAC() {
		return this.aggregateMeterAc;
	}

	@Override
	public String getAggregateFunction() {
		return this.aggregateFuction;
	}

}
