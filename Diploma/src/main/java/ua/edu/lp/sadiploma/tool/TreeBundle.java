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
		List<Integer> allNumbers = new ArrayList<Integer>();
		Component component = new Node();
		List<List<Component>> combinations = component.getAllCombinations(data);
		for (List<Component> list : combinations) {
			int result = 0;
			for (Component c : list) {
				result+=c.getValue();
			}
			allNumbers.add(result);
		}
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
