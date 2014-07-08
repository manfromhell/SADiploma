package sa.data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Solution {
	private static double GAPS_KOEF;
	private static double REP_KOEF;
	private static BundleType bundleType = BundleType.RingBundle;

	private double mSolutionEnergy = 0.0;
	private static int sum = 0;
	private Bundle bundle = null;

	public Solution(int n) {
		switch (BundleType.getType(bundleType)) {
		case 1:
			bundle = new LineBundle(new int[n]);
			break;
		default:
			bundle = new RingBundle(new int[n]);
		}
		this.mSolutionEnergy = Double.MAX_VALUE;
	}

	@Override
	public String toString() {
		return bundle.toString();
	}

	public void setSolution(Solution that) {
		this.bundle.setData(that.bundle.getData());
		this.mSolutionEnergy = that.mSolutionEnergy;
		return;
	}

	public void setData(int[] data) {
		this.bundle.setData(data);
	}

	public double getSolutionEnergy() {
		return this.mSolutionEnergy;
	}

	public void randomChange() {
		int temp = 0;
		int x = 0;
		int y = 0;

		// Get two different random numbers.
		x = new Random().nextInt(this.bundle.getDataLength());
		y = getExclusiveRandomNumber(this.bundle.getDataLength(), x);

		Bundle tmpBundle = bundle.clone();
		do{
			tmpBundle = bundle.clone();
			int randomValue =  new Random().nextInt(this.bundle.getDataSum()/2);
			randomValue = (this.bundle.getData(x)>this.bundle.getData(y)) ? randomValue : -randomValue;

			temp = tmpBundle.getData(x) - randomValue;
			tmpBundle.setData(x, tmpBundle.getData(y) + randomValue);
			tmpBundle.setData(y, temp);
			
		}while((tmpBundle.getDataSum() != bundle.getDataSum())||(tmpBundle.hasRepeats())||(tmpBundle.hasNegatives()));
		this.bundle = tmpBundle.clone();
		
		return;
	}
	
	private static int getExclusiveRandomNumber(final int high, final int except) {
		boolean done = false;
		int getRand = 0;

		while (!done) {
			getRand = new Random().nextInt(high);
			if (getRand != except) {
				done = true;
			}
		}

		return getRand;
	}

	public void computeEnergy() {
		int gaps = 0;
		int repeats = 0;

		List<Integer> allNumbers = bundle.generateCombinations();

		 //System.out.println(allNumbers);
		sortList(allNumbers);
		 //System.out.println("Sorted sums: \t"+allNumbers);

		// counting gaps
		int max = 0;
		switch (BundleType.getType(bundleType)) {
		case 1:
			max = (bundle.getDataLength() * (bundle.getDataLength() + 1)) / 2;
			break;
		default:
			max = bundle.getDataLength() * (bundle.getDataLength() - 1) + 1;
		}
		//System.out.println(bundle);
		//System.out.println(max);
		for (int i = 1; i < max; i++) {
			if (!(allNumbers.contains(i))) {
				 //System.out.println("gap " + i);
				gaps++;
			}
			// counting repeats
			int count = 0;
			for (int j : allNumbers) {
				if (i == j) {
					 //System.out.println("repeat " + i);
					count++;
				}
			}
			if (count > 1) {
				repeats += count - 1;
			}
		}

		// System.out.println("gaps " + gaps + " repeats " + repeats
		// + " max number " + allNumbers.get(allNumbers.size() - 1));
		this.mSolutionEnergy = gaps * GAPS_KOEF + repeats * REP_KOEF; // Complete
		return;
	}

	public static void sortList(List<Integer> list) {
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

	public static void setKoefs(double gapsKoef, double repKoef, BundleType type) {
		GAPS_KOEF = gapsKoef;
		REP_KOEF = repKoef;
		bundleType = type;
	}

	/**
	 * @return the sum
	 */
	public static int getSum() {
		return sum;
	}

	/**
	 * @param sum the sum to set
	 */
	public static void setSum(int sum) {
		Solution.sum = sum;
	}
}