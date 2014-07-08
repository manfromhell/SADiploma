package sa.data;

import java.util.List;

public interface Bundle extends Cloneable{
	List <Integer> generateCombinations();
	public int getDataSum();
	public int getData(int i);
	public int getDataLength();
	public String toString();
	public int[] getData();
	public void setData(int[] data);
	public void setData(int index, int value);
	public BundleType getBundleType();
	public Bundle clone();
	boolean hasRepeats();
	boolean hasNegatives();
}
