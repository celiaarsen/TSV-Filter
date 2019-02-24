/**
 * Computes the sum of the values in a field of longs
 * @author Celia Arsen
 *
 */
public class ComputeSum implements Compute {
	int sum;
	
	public ComputeSum() {
		sum = 0;
	}
	/**
	 * Adds the value to the running sum of values
	 */
	public void compute(String value) {
		try {
			sum = sum + Integer.parseInt(value);
		}
		catch(NumberFormatException e) {
			System.out.println("Can only compute SUM on 'long' field");
			System.exit(0);
		}
	}

	/**
	 * get the current sum
	 * @return the current sum
	 */
	public String getComputation() {
		return String.valueOf(sum);
	}

	/**
	 * @return an easy-to-read statement explaining the result of the computation on the compute field
	 */
	public String finalStatement(String field) {
		String statement = "Sum of " + field + ": " + sum;
		return statement;
	}

}
