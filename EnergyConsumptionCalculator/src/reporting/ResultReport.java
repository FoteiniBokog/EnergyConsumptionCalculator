package reporting;

import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.Machine;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import java.util.ArrayList;


public class ResultReport implements IResultReporter {


@Override
public int reportResultInFile(IResult result, String filename) {
	PrintWriter outputStream=null;
	String outputFileName="./output/Report.ascii";
	try {
		outputStream=new PrintWriter(new FileOutputStream(outputFileName));
	}
	catch(FileNotFoundException e) {
		System.out.println("Problem opening Report");
		System.exit(0);
	}
	for(MeasurementRecord e:objCollection) {
		outputStream.println(e.getDescription());
	}
	outputStream.println(e.getDescription());
	return 0;
}
