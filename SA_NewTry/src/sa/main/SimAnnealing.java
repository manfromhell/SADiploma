package sa.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Random;

import sa.data.Bundle;
import sa.data.BundleType;
import sa.data.LineBundle;
import sa.data.RingBundle;
import sa.data.Solution;
import sa.readers_Writers.ConfigReader;
import sa.readers_Writers.LogWriter;

public class SimAnnealing {
	private static double INITIAL_TEMPERATURE;
	private static double FINAL_TEMPERATURE;
	private static double ALPHA;
	private static int ITERATIONS_AT_TEMPERATURE;
	private static double GAPS_KOEF;
	private static double REP_KOEF;
	private static int TYPE;

	private static Bundle bundle;
	private static BundleType bundleType;

	private static Solution currentSolution = new Solution(0);
	private static Solution workingSolution = new Solution(0);
	private static Solution bestSolution = new Solution(0);

	private static double simulatedAnnealingAlgorithm(int n) {
		boolean useNew = false;
		double temperature = INITIAL_TEMPERATURE;
		currentSolution.setSolution(new Solution(n));
		workingSolution.setSolution(new Solution(n));
		bestSolution.setSolution(new Solution(n));
		// Initial setup of the solution.
		currentSolution.setData(bundle.getData());
		// Randomly perturb the solution.
		for (int i = 0; i < bundle.getDataLength(); i++) {
			currentSolution.randomChange();
		}

		currentSolution.computeEnergy();

		bestSolution.setSolution(currentSolution);
		workingSolution.setSolution(currentSolution);

		while (temperature > FINAL_TEMPERATURE) {
			for (int i = 0; i < ITERATIONS_AT_TEMPERATURE; i++) {
				useNew = false;
				workingSolution.randomChange();
				workingSolution.computeEnergy();

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
					System.out.print(bestSolution + "\tЕнергія: "
							+ bestSolution.getSolutionEnergy());
					System.out.println("\t\t Найкращий розв'язок: Точний");
					return bestSolution.getSolutionEnergy();
				}
			}
			temperature *= ALPHA;
		}

		return bestSolution.getSolutionEnergy();
	}

	public static void initializeAlgorithm(String[] args) {
		ConfigReader configReader = ConfigReader.getInstance(
				"config.properties", args);

		INITIAL_TEMPERATURE = configReader.getInitTemp();
		FINAL_TEMPERATURE = configReader.getFinalTemp();
		ALPHA = configReader.getAlpha();
		ITERATIONS_AT_TEMPERATURE = configReader.getIterationsPerTemperature();
		GAPS_KOEF = configReader.getGapsKoef();
		REP_KOEF = configReader.getRepKOEF();
		TYPE = configReader.getType();
		bundleType = BundleType.getType(TYPE);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Введіть тип в’язанки: (1 - line / 2 - ring)\n");

		try {
			TYPE = Integer.parseInt(br.readLine());
			bundleType = BundleType.getType(TYPE);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
			System.out.println("Input error. Program terminates");
			return;
		}
		System.out.println(bundleType.name());
		Solution.setKoefs(GAPS_KOEF, REP_KOEF, bundleType);
		int number = 0;
		System.out.println("Введіть кількість чисел:\n");
		try {
			number = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
			System.out.println("Input error. Program terminates");
			return;
		}
		List<Integer> list = new CombinationFinder(number, bundleType)
				.findCombination();

		switch (BundleType.getType(bundleType)) {
		case 1:
			bundle = new LineBundle(listToArray(list));
			break;
		default:
			bundle = new RingBundle(listToArray(list));
		}

		LogWriter logWriter = new LogWriter(bestSolution);
		logWriter.start();
		long startTime = System.currentTimeMillis();
		simulatedAnnealingAlgorithm(number);
		System.out.println("Час роботи: "+(System.currentTimeMillis()-startTime)+"ms");
		System.out.println("Найкраща комбінація: " + bestSolution
				+ " з енергією: " + bestSolution.getSolutionEnergy());
		logWriter.interrupt();
	}

	private static int[] listToArray(List<Integer> list) {
		int[] arr = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}

}
