package ua.edu.lp.sadiploma.tool;

import java.util.List;

public interface Bundle extends Cloneable{
	List <Integer> generateCombinations();
	public int getDataSum();
	public Component getData(int i);
	public int getDataLength();
	public String toString();
	public Component getData();
	public void setData(Component data);
	public void setData(int index, int value);
	public BundleType getBundleType();
	public Bundle clone();
	boolean hasRepeats();
	boolean hasNegatives();
}
