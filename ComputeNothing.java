/**
 * This class is a construct for the null object pattern
 * When the user does not specify a Computation, the methods will do nothing
 * @author Celia
 *
 */
public class ComputeNothing implements Compute {

	/**
	 * does not compute anything
	 */
	public void compute(String value) {}

	/**
	 * @return an empty string
	 */
	public String getComputation() {return "";}

	/**
	 * @return an empty string
	 */
	public String finalStatement(String field) {return "";}

}
