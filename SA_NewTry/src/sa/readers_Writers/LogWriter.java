/**
 * 
 */
package sa.readers_Writers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import sa.data.Solution;

/**
 * @author user
 *
 */
public class LogWriter extends Thread {
	Solution solution;
	String file = "sa" + new Date().getTime() + ".txt";

	/**
	 * 
	 */
	public LogWriter(Solution arg1) {
		solution = arg1;
	}

	@Override
	public void run() {
		try {
			super.run();
			File folder = new File("D:/SA");
			FileWriter fileWriter = new FileWriter(new File(folder, file), true);
			fileWriter.write(new Date().toString() + "\r\n");
			fileWriter.close();

			synchronized (this) {
				while (solution.getSolutionEnergy() > 0) {

					fileWriter = new FileWriter(new File(folder, file), true);
					fileWriter.write(solution.toString() + "\t\t"
							+ solution.getSolutionEnergy() + "\r\n");
					fileWriter.close();

					wait(5000);
				}
			}
		} catch (InterruptedException | IOException e) {
			System.err.println(e.toString());
			//e.printStackTrace();
		}
	}
}
