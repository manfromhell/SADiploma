package ua.edu.lp.sadiploma.tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node implements Component {
	private int index;
	private int value;
	private boolean endOfChain;
	/**
	 * @return the endOfChain
	 */
	public boolean isEndOfChain() {
		return endOfChain;
	}

	/**
	 * @param endOfChain the endOfChain to set
	 */
	public void setEndOfChain(boolean endOfChain) {
		this.endOfChain = endOfChain;
	}

	private Component parentNode;
	private List<Component> components = new ArrayList<Component>();

	public Node(int index, int value, Component parentNode) {
		this.index = index;
		this.value = value;
		this.parentNode = parentNode;
	}

	public Node(int value) {
		this.index = 1;
		this.value = value;
		this.parentNode = null;
	}

	public Node() {
		this.index = 1;
		this.value = 0;
		this.parentNode = null;

	}

	@Override
	public Component getRoot() {
		if (this.parentNode == null) {
			return this;
		}
		return this.parentNode.getRoot();
	}

	public void addComponent(Component component) {
		components.add(component);
	}

	public void removeComponent(Component component) {
		components.remove(component);
	}

	/**
	 * Use only for root component
	 */
	@Override
	public Component findComponent(int index) {
		if (this.index == index) {
			return this;
		}
		for (Component component : this.getComponents()) {
			Component foundComponent = component.findComponent(index);
			if (foundComponent != null) {
				return foundComponent;
			}
		}
		return null;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public Component getParentNode() {
		return parentNode;
	}

	@Override
	public void setParentNode(Component parentNode) {
		this.parentNode = parentNode;
	}

	@Override
	public List<Component> getComponents() {
		return components;
	}

	@Override
	public void setComponents(List<Component> components) {
		this.components = components;
	}

	@Override
	public Component generateTree(String parentCode, String data) {
		String[] parentCodeArray = parentCode.split("[, /;-]|(, )");
		int[] parentCodeArrayInt = new int[parentCodeArray.length];
		String[] dataArray = data.split("[, /;-]|(, )");
		int[] dataArrayInt = new int[dataArray.length];

		for (int i = 0; i < parentCodeArray.length; i++) {
			parentCodeArrayInt[i] = Integer.parseInt(parentCodeArray[i]);
			dataArrayInt[i] = Integer.parseInt(dataArray[i]);
		}
		System.out.println(Arrays.toString(parentCodeArrayInt) + "\n"
				+ Arrays.toString(dataArrayInt));
		Component root = new Node(dataArrayInt[0]);
		for (int i = 1; i < dataArrayInt.length; i++) {
			Component component = root.findComponent(parentCodeArrayInt[i]);
			component.addComponent(new Node(i + 1, dataArrayInt[i], component));
		}
		return root;
	}

	@Override
	public int getSize() {
		if (this.components.size() > 0) {
			int result = 1;
			for (Component c : components) {
				result += c.getSize();
			}
			return result;
		} else {
			return 1;
		}
	}

	@Override
	public List<Integer> getAllValues() {
		List<Integer> result = new ArrayList<Integer>();
		result.add(this.value);
		for (Component c : this.components) {
			result.addAll(c.getAllValues());
		}
		return result;
	}

	@Override
	public String toString() {
		return "Node [index=" + index + ", value=" + value
				+ ", components=\n\t" + components + "]";
	}

	@Override
	public int[][] createTable(List<Integer> parentCode) { // List<List<Integer>>
		int[][] result = new int[this.getSize()][this.getSize()];
		for (int col = 1; col < getSize(); col++) {
			result[parentCode.get(col)-1][col] = 1;
		}
		return result;
	}
}
