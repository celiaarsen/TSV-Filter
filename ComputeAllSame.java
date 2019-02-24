/**
 * This class keeps track of whether a series of values in a stream are the same 
 * @author Celia Arsen
 *
 */
public class ComputeAllSame implements Compute {
	private static boolean allSame;
	String lastItem;
	
	public ComputeAllSame() {
		allSame=true;
		lastItem="";
	}

	/**
	 * @param String the value that will be checked for consistency with previous records
	 */
	public void compute(String value) {
		if(lastItem.equals("")) {
			lastItem = value;
		}
		else if(allSame==true) {
			if(!lastItem.equals(value)){
				allSame = false;
			}
		}
	}

	/**
	 * @return return the value of allSame
	 */
	public String getComputation() {
		if (allSame==true) {
			return "True";
		}
		else {
			return "False";
		}
	}

	/**
	 * @return an easy-to-read statement explaining the result of the computation on the compute field
	 */
	public String finalStatement(String field) {
		String statement = field + " all same?: " + getComputation();
		return statement;
	}
}
