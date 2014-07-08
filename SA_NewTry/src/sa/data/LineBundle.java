package sa.data;

import java.util.ArrayList;
import java.util.List;

public class LineBundle implements Bundle {
	private int[] data;

	public LineBundle(int[] data) {
		this.data = data;
	}

	@Override
	public List<Integer> generateCombinations() {
		List<Integer> resultList = new ArrayList<Integer>();
		for (int k = 0; k < getDataLength(); k++) { // current number position

			int tmpSum = 0;
			for (int i = k + 1; i <= getDataLength(); i++) { // count of numbers
																// for sum
				tmpSum = 0;
				for (int j = k; j < i; j++) { // counter for sum
					tmpSum += this.getData(j);
				}
				resultList.add(tmpSum);
			}
		}
		return resultList;
	}

	@Override
	public int getData(int i) {
		return data[i];
	}

	public int getDataLength() {
		return data.length;
	}

	@Override
	public String toString() {
		String result = "";
		for (int val : data) {
			result += val + " ";
		}
		return result;

	}

	@Override
	public int[] getData() {
		return data;
	}

	@Override
	public void setData(int[] data) {
		this.data = data;

	}

	@Override
	public void setData(int index, int value) {
		this.data[index] = value;

	}

	@Override
	public BundleType getBundleType() {
		return BundleType.LineBundle;
	}
	
	public Bundle clone() {
		Bundle bundle = new LineBundle(getData());
		return bundle;
	}

	@Override
	public int getDataSum() {
		int result = 0;
		for (int value : data){
			result += value;
		}
		return result;
	}

	@Override
	public boolean hasRepeats() {
		for (int i = 0 ; i < data.length-1 ; i++){
			for (int j = i+1 ; j < data.length ; j++){
				if (data[i] == data[j]){
					return true;
				}
			}			
		}
		return false;
	}

	@Override
	public boolean hasNegatives() {
		for (int val : getData()){
			if (val < 1){
				return true;
			}
		}
		return false;
	};
	
}
