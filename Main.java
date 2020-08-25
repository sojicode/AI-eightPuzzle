
import java.util.Scanner;

public class Main {
	
	final static String EASY = "134862705";
	final static String MEDIUM = "281043765";
	final static String HARD = "567408321";
	final static String GOAL = "123804765";
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);     // scans the console.

		System.out.println("Enter a number of level option: ");  // prints to console
		
		System.out.println("1. EASY");
		System.out.println("2. MEDIUM");
		System.out.println("3. HARD");
		
		int option = scanner.nextInt(); 
		String level = "";
		
		switch(option) {
		case 1:
			level = EASY;
			break;
		case 2:
			level = MEDIUM;
			break;
		case 3:
			level = HARD;
			break;
			
		}
		
		System.out.println("Enter a number of algorithm: ");
		
		System.out.println("1. BFS");
		System.out.println("2. DFS");
		System.out.println("3. IDS");
		System.out.println("4. UCS");
		System.out.println("5. GBS");
		System.out.println("6. ASTAR");
		
		int option2 = scanner.nextInt(); 
		
		switch(option2) {
		case 1:
			BreadthFirst bfs = new BreadthFirst();
	 		bfs.breadthFirstSearch(level);
			break;
		case 2:
			DepthFirst dfs = new DepthFirst();
			dfs.depthFirstSearch(level);
			break;
		case 3:
			IDS ids = new IDS();
			ids.iterativeDeepening(level, 35);
			break;
		case 4:
			UniformCost uc = new UniformCost();
			uc.uniformCostSearch(level);
			break;
		case 5:
			BestFirst gbf = new BestFirst();
			gbf.BestFirstSearch(level);
			break;
		case 6:
			Astar astar = new Astar();
			
			System.out.println("Enter a number of H-type: ");
			System.out.println("1. H1");
			System.out.println("2. H2");
			System.out.println("3. H3");
			
			int option3 = scanner.nextInt(); 
			switch(option3) {
			case 1:
				astar.aStar(level, "h1");
				break;
			case 2:
				astar.aStar(level, "h2");
				break;
			case 3:
				astar.aStar(level, "h3");
				break;
			}
			
			break;
			
		}
		
		scanner.close();
	
	
	}
	
}
