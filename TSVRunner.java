import java.util.Scanner;
import java.io.*;
/**
 * Runs through a use case of the TSV streams and pipes system
 * It uses an instance of the TSVFilter to select and compute parts of the stream
 * This outputs a file. The runner then prints out each line of the file.
 * @author Celia Arsen
 *
 */
public class TSVRunner {

	public static void main(String[] args) throws FileNotFoundException {
		TSVFilter myFilter = new TSVFilter
				.Builder("testInput.tsv") //change this to args[0] for easy command line arguments
				.select("Surname", "Arsen")
				.compute("zipcod", Terminal.MAX)
				.done();
		System.out.println(myFilter);
		File outPut = new TSVPipeline(myFilter).doit();
		Scanner scanner = new Scanner(outPut);
		while(scanner.hasNext()) {
			System.out.println(scanner.nextLine());

		}
		scanner.close(); 
	}
}
