package ua.edu.lp.sadiploma.tool;

import java.util.Random;

public class SimAnnealing {
	private static double INITIAL_TEMPERATURE;
	private static double FINAL_TEMPERATURE;
	private static double ALPHA;
	private static int ITERATIONS_AT_TEMPERATURE;
	private static double GAPS_KOEF;
	private static double REP_KOEF;
//	private static int TYPE;

	private static Bundle bundle;
	private static BundleType bundleType;

	private static Solution currentSolution;
	private static Solution workingSolution;
	private static Solution bestSolution;

	private static double simulatedAnnealingAlgorithm(Component component) {
		boolean useNew = false;
		double temperature = INITIAL_TEMPERATURE;
		currentSolution.setSolution(new Solution(component));
		workingSolution.setSolution(new Solution(component));
		bestSolution.setSolution(new Solution(component));
		// Initial setup of the solution.
		currentSolution.setBundle(bundle);
		// Randomly perturb the solution.
		for (int i = 0; i < bundle.getDataLength(); i++) {
			currentSolution.randomChange();
		}

		currentSolution.computeTargetFunction();

		bestSolution.setSolution(currentSolution);
		workingSolution.setSolution(currentSolution);

		while (temperature > FINAL_TEMPERATURE) {
			for (int i = 0; i < ITERATIONS_AT_TEMPERATURE; i++) {
				useNew = false;
				workingSolution.randomChange();
				workingSolution.computeTargetFunction();

				if (workingSolution.getSolutionEnergy() <= currentSolution
						.getSolutionEnergy()) {
					useNew = true;
				} else {
					double test = new Random().nextDouble(); // Get random value
																// between 0.0
																// and 1.0
					double delta = workingSolution.getSolutionEnergy()
							- currentSolution.getSolutionEnergy();
					double calc = Math.exp(-delta / temperature);
					if (calc > test) {
						useNew = true;
					}
				}

				if (useNew) {
					useNew = false;
					currentSolution.setSolution(workingSolution);
					if (currentSolution.getSolutionEnergy() < bestSolution
							.getSolutionEnergy()) {
						bestSolution.setSolution(currentSolution);
						// System.out.println(bestSolution + "\t\t" +
						// bestSolution.getSolutionEnergy() +"\t\t"+
						// temperature);

					}
				} else {
					workingSolution.setSolution(currentSolution);
				}

				if (bestSolution.getSolutionEnergy() == 0) {
					System.out.print(bestSolution + "\t������: "
							+ bestSolution.getSolutionEnergy());
					System.out.println("\t\t ��������� ����'����: ������");
					return bestSolution.getSolutionEnergy();
				}
			}
			temperature *= ALPHA;
		}

		return bestSolution.getSolutionEnergy();
	}

}
