/**
 * Interface that requires classes to conduct some computation based on a string value,
 * return that computation, and represent it in a clear human-readable statement.
 * @author Celia Arsen
 *
 */
public interface Compute {
	public void compute(String value);
	public String getComputation();
	public String finalStatement(String field);
}
