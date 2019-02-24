/**
 * This class builds a tsv filter, based on user inputs
 * @author Celia Arsen
 *
 */
public class TSVFilter {
	private final String inputFile;
	private final String selectField;
	private final String selectValue;
	private final String computeField;
	private final Terminal computeOperation;
	
	private TSVFilter(Builder inBuilder) {
		this.inputFile = inBuilder.inputFile;
		this.selectField = inBuilder.selectField;
		this.selectValue = inBuilder.selectValue;
		this.computeField = inBuilder.computeField;
		this.computeOperation = inBuilder.computeOperation;
	}
	
	/**
	 * 
	 * @return the file being read for input
	 */
	public String getInputFile() {
		return this.inputFile;
	}
	
	/**
	 * 
	 * @return the field being selected
	 */
	public String getSelectField() {
		return this.selectField;
	}
	
	/**
	 * 
	 * @return the value being selected
	 */
	public String getSelectValue() {
		return this.selectValue;
	}
	
	/**
	 * 
	 * @return the field to be computed on
	 */
	public String getComputeField() {
		return this.computeField;
	}
	
	/**
	 * 
	 * @return the type of operation to be performed on the compute field
	 */
	public Terminal getComputeOperation() {
		return this.computeOperation;
	}
	
	/**
	 * @return Easy to read string representation of the choices the user has made in the TSVFilter
	 */
	public String toString() {
		return "TSVFilter {" + "\n"
				+ "  file name = "     + inputFile     + "\n"
				+ ", selected field = " + selectField + "\n"
				+ ", selected value = "  + selectValue  + "\n"
				+ ", compute field = " + computeField + "\n"
				+ ", operation = " + computeOperation + "\n"
				+ "}" + "\n";
	}
	
	/**
	 * A builder class for constructing a TSVFilter 
	 * @author Celia Arsen
	 *
	 */
	public static class Builder{
		
		public Builder(String inFile) {
			this.inputFile = inFile;
		}

		/**
		 * @param field to be selected
		 * @param value to select on
		 * @return this Builder
		 */
		public Builder select(String field, String value) {
			this.selectField = field;
			this.selectValue = value;
			return this;
		}
		
		/**
		 * @param field to be selected 
		 * @param value to select on
		 * @return this Builder
		 */
		public Builder select(String field, long value) {
			this.selectField = field;
			this.selectValue = String.valueOf(value);
			return this;
		}
		
		/**
		 * @param field to compute on
		 * @param computation type
		 * @return this Builder
		 */
		public Builder compute(String field, Terminal computation) {
			this.computeField = field;
			this.computeOperation = computation;
			return this;		
		}
		
		/**
		 * @return a new instance of a TSVFilter, with the parameters specified by this Builder
		 */
		public TSVFilter done() {
			return new TSVFilter(this);
		}
		
		//these are initialized to follow a null object pattern.
		private String inputFile = "";
		private String selectField = "";
		private String selectValue = "";
		private String computeField = "";
		private Terminal computeOperation = Terminal.NONE;
	}
}
