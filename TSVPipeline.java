/**
 * This class takes a TSVFilter and returns a new file of filtered and computed output,
 * based on use input.
 * @author Celia Arsen
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TSVPipeline{
	
	private File inFile;
	private Scanner scanner;
	private File outFile;
	private String[] headers;
	private long dataTypes;
	private Selection selection;	
	private String computeField;
	private Compute computation;	
	private FileWriter outWriter;
	private int computeColumnNum;
	
	public TSVPipeline(TSVFilter tsvFilter) {
		inFile = new File(tsvFilter.getInputFile());
		scanner = makeScanner(inFile);
		outFile = new File("output.tsv");
		outWriter = makeFileWriter();
		headers = makeHeader();
		dataTypes = makeDataTypes();
		computeField = tsvFilter.getComputeField();
		computation = makeComputationType(tsvFilter.getComputeOperation());
		selection = new Selection(tsvFilter.getSelectField(), tsvFilter.getSelectValue(), headers);	
		computeColumnNum = computeColumnNum(computeField);
	}
	
	/**
	 * 
	 * @return a File containing the filtered stream
	 */
	public File doit() {
		return stream();
	}	
	
	//Streams through input data and writes valid output to a new file called output.tsv
	private File stream() {		
		while(scanner.hasNextLine()) { 
			String nextRecord = scanner.nextLine();
			String[] recordArray = nextRecord.split("\t");
			if(isValidRecord(recordArray) && selection.matchesFilter(recordArray)) { 	
				computation.compute(recordArray[computeColumnNum]);
				try {
					outWriter.write(nextRecord + "\r\n");
				} 
				catch (IOException e) {
						e.printStackTrace();
				}
			}			
		}
		try {
			if(!computeField.equals("")){
				String statement = computation.finalStatement(computeField);
				outWriter.write(statement);
				outWriter.close();
				scanner.close();
			}
			else {
				outWriter.close();
				scanner.close();
			}
		} 
		catch (IOException e) {
			System.out.println("IOException occured");
			System.exit(0);
		}
		return outFile;
	}
	
	//Check to make sure that the file actually exists and make a scanner
	private Scanner makeScanner(File file) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
			System.exit(0);
		}
		return scanner;
	}
	
	//Make a FileWriter
	private FileWriter makeFileWriter() {
		FileWriter writer = null;
		try {
			writer = new FileWriter(outFile);
		}
		catch(IOException e) {
			System.out.println("IOException occured");
			System.exit(0);
		}
		return writer;
	}
	
	//make sure header exists and is valid
	private String[] makeHeader() {
		String headers = null;
		String[] headerArray = null;
		try {
			headers = scanner.nextLine();
		}
		catch(NoSuchElementException e) {
			System.out.println("Input file does not contain text");
			System.exit(0);
		}
		headerArray = headers.split("\t");
		try {
			outWriter.write(formatRow(headerArray));
		} 
		catch (IOException e) {
			System.out.println("IOException occured");
			System.exit(0);
		}
		return headerArray;
	}
	
	//format row to be written into a tsv file	
	private String formatRow(String[] names) {
		String row = "";
		for (String s: names) {
			row = row + s + "\t";
		}		
		return row + "\n";
	}
	
	//make sure the data types are there and in valid format
	private long makeDataTypes() {		
		if(scanner.hasNext()){
			String[] dataTypeArray = scanner.nextLine().split("\t");
			try{
				outWriter.write(formatRow(dataTypeArray));
			}
			catch(IOException e) {
				System.out.println("IOException occured");
				System.exit(0);
			}
			StringBuilder sb = new StringBuilder(dataTypeArray.length);
			try {
				for(String s : dataTypeArray) {
					if(s.equals("long")) {
						sb.append('1');
					}
					else if(s.equals("String")) {
						sb.append('2');
					}
					else{
						throw new Exception();
					}
				}
			}
			catch(Exception e) {
				System.out.println("Invalid data types. Must be String or long");
				System.exit(0);
				}
			try {
				if(sb.toString().length()!=headers.length) {
					throw new Exception();
				}
			}
			catch(Exception e) {
				System.out.println("Header/datatype mismatch");
				System.exit(0);						
			}
			return Long.parseLong(sb.toString());
		}
		else {
			System.out.println("File does not contain data types");
			System.exit(0);
			return -1;
		}
	}	
	
	//Determines what type of Computation class to instantiate
	private Compute makeComputationType(Terminal computationType) {
		if (computationType==Terminal.MAX) {
			return new ComputeMax();
		}
		else if (computationType==Terminal.MIN) {
			return new ComputeMin();
		}
		else if (computationType==Terminal.ALLSAME) {
			ComputeAllSame allSame = new ComputeAllSame();
			return allSame;
		}
		else if (computationType==Terminal.COUNT) {
			return new ComputeCount();
		}
		else if (computationType==Terminal.SUM) {
			return new ComputeSum();
		}
		else if (computationType==Terminal.FIRSTDIFF) {
			return new ComputeFirstDiff();
		}
		else {
			return new ComputeNothing();
		}
	}
			
	public int computeColumnNum(String field) {
		int columnNum = 0;
		while(columnNum<headers.length && !headers[columnNum].equals(field)) {
				columnNum++;
		}
		if(columnNum<headers.length) {
			return columnNum;
		}
		if(computeField.equals("")) {
			return 0;
		}

		else{
			System.out.println("Specified compute field does not exist in file");
			System.exit(0);
			return -1;
		}		
	}
		
	//Check to make sure data is in appropriate format
	private boolean isValidRecord(String[] record) {
		boolean good = true;
		int dataCopy = (int)dataTypes;
		//Make sure that the long values can be represented as long 
		try {
			for(int i=record.length-1; i>=0; i--) {

				if(dataCopy%10==1) {
					Long.parseLong(record[i]);
				}
				dataCopy = dataCopy/10;
			}
		}
		catch(Exception e) {
			good = false;
		}
		if(headers.length!=record.length) {
			good = false;
		}
		return good;
	}
	
}