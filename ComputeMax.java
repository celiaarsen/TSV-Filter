/**
 * Computes the maximum value in a field
 * @author Celia
 *
 */
public class ComputeMax implements Compute{
	String max;
	public ComputeMax() {
		max = "";
	}
	
	/**
	 * Reassigns max if a larger value has been processed
	 */
	public void compute(String value) {
		if(max.equals("")) {
			max = value;
		}
		else if(value.compareTo(max)>0) {
			max = value;
		}		
	}

	/**
	 * @return the maximum value processed in the stream
	 */
	public String getComputation() {
		return max;
	}

	/**
	 * @return an easy-to-read statement explaining the result of the computation on the compute field
	 */
	public String finalStatement(String field) {
		String statement =  "Maximum " + field + ": " + max;
		return statement;
	}
}
