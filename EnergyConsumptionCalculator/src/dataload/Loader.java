
package dataload;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import datamodel.MeasurementRecord;
import java.io.FileInputStream;
//import java.io.FileReader;
import java.io.FileNotFoundException;


class Loader implements ILoader<MeasurementRecord>{
	private String Date;
	private String Time;
	private Double Sub_metering_kitchen;
	private Double Sub_metering_laundry;
	private Double Sub_metering_AC;
	
	//private Scanner reader;
	
	/*
	public Loader() {
		reader=new Scanner(System.in);
		
	}
	public Loader(String empPath) {
		reader=new Scanner(System.in);
		
	}
	
	public String choosePath(String fileName){
		System.out.println("Type the Path of the File you want");
		fileName=reader.nextLine();
		return fileName;
	}*/
		
	public int constructObjectFromRow(String[] tokens, ArrayList<MeasurementRecord>objCollection){
		this.Date=tokens[0];
		this.Time=tokens[1];
		this.Sub_metering_kitchen= Double.parseDouble(tokens[6]);
		this.Sub_metering_laundry= Double.parseDouble(tokens[7]);
		this.Sub_metering_AC=Double.parseDouble(tokens[8]);
			
		MeasurementRecord i =new MeasurementRecord(Date,Time,Sub_metering_kitchen, Sub_metering_laundry,Sub_metering_AC);
		objCollection.add(i);
		//System.out.println(i);
		return 0;
	}
	
		@Override
		public int load(String fileName, String delimeter, boolean hasHeaderLine, int numFields,ArrayList<MeasurementRecord> objCollection) {
			if(numFields<1) {
				System.out.println("Wrong number of fields,less than 1");
				System.exit(0);
			}
			Scanner inputStream=null;
			
			try {
				inputStream = new Scanner(new FileInputStream(fileName));
	
			} catch (FileNotFoundException e) {
				System.out.println("Problem opening file: " + fileName);
				return -1;
				//System.exit(0);
			}
	
			int count = 0;
	
			//process the title
			if(hasHeaderLine){
				String titleLine = inputStream.nextLine();
				count++;
			}
			String line = "";
			//process the actual rows one by one
			while (inputStream.hasNextLine()) {
				line = inputStream.nextLine();
				count++;
				StringTokenizer tokenizer = new StringTokenizer(line, delimeter);
				if(tokenizer.countTokens() != numFields){
					System.out.println("Wrong Input format in file " + fileName +". I found " + tokenizer.countTokens() + " values, but I expect " + numFields + " values per row!");
					continue;
					//System.exit(0);				
				}
	
				String[] tokens =  new String[numFields];
				for (int i=0; i< numFields;i++){
					tokens[i] = tokenizer.nextToken();
				}
				int objConstructionErrorCode;
				objConstructionErrorCode = constructObjectFromRow(tokens,objCollection);
				if (objConstructionErrorCode !=0){
					System.out.println("ObjParsingError. I found a problem at line " + count + " of file " + fileName);
					System.exit(0);
				}
			
			}
			inputStream.close();
			System.out.println("Processed"+count+"rows and loanded"+objCollection.size()+"objects!");
			return count;
		}
}
	
	 
