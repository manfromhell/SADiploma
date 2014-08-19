package ua.edu.lp.sadiploma.tool;

import java.util.ArrayList;
import java.util.List;

public class TreeBundle implements Bundle {
	private Component data;

	public TreeBundle(Component data) {
		this.data = data;
	}

	@Override
	public List<Integer> generateCombinations() {

		// TODO rewrite totally
		List<Integer> allNumbers = new ArrayList<Integer>();

		for (int k = 0; k < getDataLength(); k++) { // current numberposition 
			// List<Integer> tmpList = new ArrayList<Integer>(); int
			int tmpSum = 0;
			for (int i = 1; i < getDataLength(); i++) { // count of numbers
				// for sum tmpSum = 0; for (int j = 0; j < i; j++) { //counter
				// for sum
				tmpSum += this.getData((i + k) % this.getDataLength()).getValue();
			}
			allNumbers.add(tmpSum);
		}
		int last = 0;
		for (int i : this.data.getAllValues()) {
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

	@Override
	public Component getData(int i) {
		return data.getRoot().findComponent(i);
	}

	public int getDataLength() {
		return data.getAllValues().size();
	}

	@Override
	public String toString() {
		return data.toString();

	}

	@Override
	public Component getData() {
		return data;
	}

	@Override
	public void setData(Component data) {
		this.data = data;

	}

	@Override
	public void setData(int index, int value) {
		this.data.getRoot().findComponent(index).setValue(value);
	}

	@Override
	public int getDataSum() {
		int result = 0;
		for (int value : this.data.getAllValues()) {
			result += value;
		}
		return result;
	}

	@Override
	public boolean hasRepeats() {
		List<Integer> intData = data.getAllValues();
		for (int i = 0; i < intData.size() - 1; i++) {
			for (int j = i + 1; j < intData.size(); j++) {
				if (intData.get(i).equals(intData.get(j))) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasNegatives() {
		for (int val : data.getAllValues()) {
			if (val < 1) {
				return true;
			}
		}
		return false;
	};
}
