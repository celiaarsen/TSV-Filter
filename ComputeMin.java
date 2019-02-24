/**
 * Computes the minimum value in a field
 * @author Celia Arsen
 *
 */
public class ComputeMin implements Compute {

	String min;
	public ComputeMin() {
		min = "";
	}

	/**
	 * Reassigns min if a smaller value has been processed
	 */
	public void compute(String value) {
		if(min.equals("")) {
			min = value;
		}
		else if(value.compareTo(min)<0) {
			min = value;
		}		
	}

	/**
	 * @return the minimum value processed in the stream
	 */
	public String getComputation() {
		return min;
	}

	/**
	 * @return an easy-to-read statement explaining the result of the computation on the compute field
	 */
	public String finalStatement(String field) {
		String statement =  "Minimum " + field + ": " + min;
		return statement;
	}

}
