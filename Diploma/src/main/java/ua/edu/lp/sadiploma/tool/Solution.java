package ua.edu.lp.sadiploma.tool;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Solution {
	private static double GAPS_KOEF;
	private static double REP_KOEF;
	// private static BundleType bundleType = BundleType.RingBundle;

	private double solutionEnergy = 0.0;
	private Bundle bundle = null;

	public Solution(Component component) {
		/*
		 * switch (BundleType.getType(bundleType)) { case 1: bundle = new
		 * LineBundle(new int[n]); break; default: bundle = new RingBundle(new
		 * int[n]); }
		 */
		bundle = new TreeBundle(component);
		this.solutionEnergy = Double.MAX_VALUE;
		GAPS_KOEF = 1;
		REP_KOEF = 1;
	}

	public Solution(Component component, double gapsKoef, double repKoef) {
		/*
		 * switch (BundleType.getType(bundleType)) { case 1: bundle = new
		 * LineBundle(new int[n]); break; default: bundle = new RingBundle(new
		 * int[n]); }
		 */
		bundle = new TreeBundle(component);
		this.solutionEnergy = Double.MAX_VALUE;
		GAPS_KOEF = gapsKoef;
		REP_KOEF = repKoef;
	}

	@Override
	public String toString() {
		return bundle.toString();
	}

	public void setSolution(Solution that) {
		this.bundle = new TreeBundle(that.bundle.getComponent());
		this.solutionEnergy = that.solutionEnergy;
		return;
	}

	public double getSolutionEnergy() {
		return this.solutionEnergy;
	}

	public void randomChange() {
		int temp = 0;
		int x = 0;
		int y = 0;

		// Get two different random numbers.
		x = new Random().nextInt(this.bundle.getDataLength());
		y = getExclusiveRandomNumber(this.bundle.getDataLength(), x);

		Bundle tmpBundle = bundle.clone();
		do {
			tmpBundle = bundle.clone();
			int randomValue = new Random()
					.nextInt(this.bundle.getDataSum() / 2);
			randomValue = (this.bundle.getData(x).getValue() > this.bundle
					.getData(y).getValue()) ? randomValue : -randomValue;

			temp = tmpBundle.getData(x).getValue() - randomValue;
			tmpBundle.setData(x, tmpBundle.getData(y).getValue() + randomValue);
			tmpBundle.setData(y, temp);

		} while ((tmpBundle.getDataSum() != bundle.getDataSum())
				|| (tmpBundle.hasRepeats()) || (tmpBundle.hasNegatives()));
		this.bundle = tmpBundle.clone();

		return;
	}

	private static int getExclusiveRandomNumber(final int high, final int except) {
		int getRand = 0;
		do {
			getRand = new Random().nextInt(high);
		} while (getRand == except);
		return getRand;
	}

	public void computeEnergy() {
		int gaps = 0;
		int repeats = 0;

		List<Integer> allNumbers = bundle.generateCombinations();

		// System.out.println(allNumbers);
		sortList(allNumbers);
		// System.out.println("Sorted sums: \t"+allNumbers);

		// counting gaps
		int max = allNumbers.get(allNumbers.size() - 1);
		// System.out.println(max);
		for (int i = 1; i < max; i++) {
			if (!(allNumbers.contains(i))) {
				// System.out.println("gap " + i);
				gaps++;
			}
			// counting repeats
			int count = 0;
			for (int j : allNumbers) {
				if (i == j) {
					// System.out.println("repeat " + i);
					count++;
				}
			}
			if (count > 1) {
				repeats += count - 1;
			}
		}

		// System.out.println("gaps " + gaps + " repeats " + repeats
		// + " max number " + allNumbers.get(allNumbers.size() - 1));
		this.solutionEnergy = gaps * GAPS_KOEF + repeats * REP_KOEF; // Complete
		return;
	}

	private static void sortList(List<Integer> list) {
		int[] tmpArray = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			tmpArray[i] = list.get(i);
		}
		Arrays.sort(tmpArray);
		list.clear();
		for (int i : tmpArray) {
			list.add(i);
		}
	}

}