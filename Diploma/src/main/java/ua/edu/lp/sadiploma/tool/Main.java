package ua.edu.lp.sadiploma.tool;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

	public static void main(String[] args) {
		new Node();
		/*
		 * Component root = new Node(1); root.addComponent(new Node(2,2,root));
		 * root.addComponent(new Node(3,3,root)); root.addComponent(new
		 * Node(4,4,root)); System.out.println("Root!: "+root);
		 * System.out.println("Found: "+root.findComponent(3));
		 */
		 Component component = Node.generateTree("0,1,2,3,4,5,6",
		 "1 3 5 6 7 10 2");
		// Component component = Node.generateTree("0 1 1 1 1 1 1 1",
		// "1,2,3,4,5,6,7,8");
//		Component component = Node.generateTree("0 1 2 3 4 1 6 7",
//				"1,2,3,4,5,6,7,8");
		System.out.println(component);
		Solution solution = new Solution(component,1.0,100.0);
		solution.computeTargetFunction();
		System.out.println(solution.getSolutionEnergy());
		// component.findComponent(4).swap(component.findComponent(7));
		// System.out.println(component);
		/*
		 * System.out.println(component.getSize());
		 * System.out.println(Arrays.deepToString
		 * (component.createTable(Arrays.asList
		 * (0,1,1,1,3,3,4,6))).replaceAll("\\], \\[", "\\],\n \\["));
		 * System.err.println(component);
		 */

		// System.out.println(component.getAllCombinations(component));
//		System.out.println("All combinations: ");
//		for (List<Component> list : component.getAllCombinations(component)) {
//			for (Component c : list) {
//				System.out.print(c.getIndex() + " ");
//			}
//			System.out.println();
//		}

		System.out.println("All sums:");
		Bundle bundle = new TreeBundle(component);
		List<Integer> allCombinations = bundle.generateCombinations();
		System.out.println(allCombinations);
		
//		Solution solution = new Solution(component,1,100);
//		solution.randomChange();
//		System.out.println(solution.toString());
		SimAnnealing simAnnealing = new SimAnnealing(component, new SAConfig(10, 100, 0.5, 5, 1.0, 100.0));
		simAnnealing.start();
//		try {
//			simAnnealing.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(simAnnealing);
		System.out.println("best:"+simAnnealing.getBestSolution()+"\nwith energy "+simAnnealing.getBestSolution().getSolutionEnergy());
		allCombinations = simAnnealing.getBestSolution().getBundle().generateCombinations();
		System.out.println(allCombinations);
	}
}