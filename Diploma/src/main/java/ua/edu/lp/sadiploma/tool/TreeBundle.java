package ua.edu.lp.sadiploma.tool;

import java.util.ArrayList;
import java.util.List;

public class TreeBundle extends LineBundle {
	public TreeBundle(int[] data) {
		super(data);
	}

	@Override
	public List<Integer> generateCombinations() {

		List<Integer> allNumbers = new ArrayList<Integer>();
		for (int k = 0; k < getDataLength(); k++) { // current number position
			// List<Integer> tmpList = new ArrayList<Integer>();
			int tmpSum = 0;
			for (int i = 1; i < getDataLength(); i++) { // count of numbers
														// for sum
				tmpSum = 0;
				for (int j = 0; j < i; j++) { // counter for sum
					tmpSum += this.getData((j + k) % this.getDataLength());
				}
				allNumbers.add(tmpSum);

			}
		}
		int last = 0;
		for (int i : this.getData()){
			last += i;
		}
		allNumbers.add(last);
		return allNumbers;
	}

	@Override
	public BundleType getBundleType() {
		return BundleType.TreeBundle;
	}
	@Override
	public Bundle clone() {
		Bundle bundle = new TreeBundle(getData());
		return bundle;
	}
}
