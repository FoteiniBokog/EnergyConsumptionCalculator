package mainengine;

import java.util.ArrayList;

import datamodel.IResult;
import datamodel.MeasurementRecord;

public class MainEngineFactory{

	public IMainEngine createMainEngine(String engineType) {

		
		if(engineType.equals("MainEngine")){
			
			return  new MainEngine();
		}
		else {
			return null;
		}
	}
}	