package ua.edu.lp.sadiploma.tool;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private List<Component> components = new ArrayList<Component>();
	
	public void addComponent(Component component){
		components.add(component);
	}
	
	public void removeComponent(Component component){
		components.remove(component);
	}

}
