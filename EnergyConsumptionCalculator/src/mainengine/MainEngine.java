package mainengine;

import java.util.ArrayList;

import dataload.ILoader;
import dataload.LoaderFactory;
import datamodel.IResult;
import datamodel.MeasurementRecord;
import timeaggregation.Aggregator;

public class MainEngine implements IMainEngine{
		private ArrayList<MeasurementRecord>myData;
		private ILoader<MeasurementRecord>dataLoader;
		private LoaderFactory dataLoaderFactory;
		private String fileName;
		private String delimeter;
		private Boolean hasHeaderLine;
		private int numFields;
		
		public MainEngine() {
			myData=new ArrayList<MeasurementRecord>();
			dataLoaderFactory=new LoaderFactory();
			dataLoader=dataLoaderFactory.createLoader("Loader");
		}
		public MainEngine(String fileName,String delimeter,Boolean hasHeaderLine,int numFields) {
			fileName="myFile";
			delimeter="\t";
			hasHeaderLine=true;
			numFields=5;
		}

		@Override/**loads data from input file**/
		public int loadData(String fileName, String delimeter, Boolean hasHeaderLine, int numFields,ArrayList<MeasurementRecord> objCollection) {
			return dataLoader.load(fileName,delimeter,hasHeaderLine,numFields,myData);
		}
		
		@Override
		public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggregatorType,String aggFunction, String description) {
			
			return new Aggregator("aggregatorType").aggregateByTimeUnit(inputMeasurements, aggFunction, description);
		}


		@Override
		public int reportResultInFile(IResult result, String reportType, String filename) {
			
			return 0;
		}





}
