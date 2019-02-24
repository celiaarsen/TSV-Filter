/**
 * This class determines whether a record in an input stream matches a select filter
 * @author Celia Arsen
 */

public class Selection {

	String field;
	String value;
	String[] headers;
	int columnNum;

	public Selection(String field, String value, String[] headers) {
		this.field = field;
		this.value = value;
		this.headers = headers;
		this.columnNum = computeColumnNum();
	}
	
	/**
	 * Checks a record from an input stream to see if it matches the filter specified in the
	 * constructor of Selection. If the specified filter field isn't a header in the dataset, there 
	 * will be no matches. If no filter field is specified at all, all records will be matches.
	 * @param record the row of data to be check
	 * @return boolean, whether the record matches the filter or not
	 */
	public boolean matchesFilter(String[] record) {
		boolean matches = false;
		if(field.equals("")) { //No selection was made 
			matches =true;
		}
		else if(record[columnNum].equals(value)) {	
			matches=true;
		}
		else {
			matches = false;
		}		
		return matches;
	} 
	
	//Get the column number of the computeField
	//If the compute field does not exist in the file, returns -1
	private int computeColumnNum() {
		int columnNum = 0;
		if(field.equals("")) {
			return -1;
		}
		else {
			while(columnNum<headers.length && !headers[columnNum].equals(field)) {
				columnNum++;
			}
			if(columnNum<headers.length) {
				return columnNum;
			}
			else{
				System.out.println("Specified select field does not exist in file");
				return -1;
			}	
		}			
	}		
}


