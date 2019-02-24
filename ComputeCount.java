/**
 * Computes the number of records in the field (i.e. in the output)
 * @author Celia Arsen
 *
 */
public class ComputeCount implements Compute {	
	private static int count;
	
	public ComputeCount() {
		count = 0;
	}
	
	/**
	 * Increments the count by one
	 */
	public void compute(String value) {
		count++;
	}
	
	/**
	 * get the value of count
	 * @return the value of count, currently
	 */
	public String getComputation() {
		return Integer.toString(count);
	}

	/**
	 * @return an easy-to-read statement explaining the result of the computation on the compute field
	 */
	public String finalStatement(String field) {
		String statement = field + " count : " + count;
		return statement;
	}
}
