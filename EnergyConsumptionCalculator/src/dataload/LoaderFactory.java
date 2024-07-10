package dataload;

import datamodel.MeasurementRecord;

public class LoaderFactory {
	public ILoader<MeasurementRecord> createLoader(String className) {
		if(className.equals("Loader")) {
			return new Loader();
		}
		System.out.println("If the code got up to here, you passed a wrong argument to the LoaderFactory");
		return null;
		}
}
