import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/*
 *  Author: Gavriel Tsioni 
 * 
 */
class TriangleNode{
	private int value;
	//Sum will hold the largest path that is below the node's sum
	private int sum;
	//Debug only:
	private int id;
	private TriangleNode leftChild;
	private TriangleNode rightChild;
	public TriangleNode(int value){
		this.value = value;
		sum = 0;
	}
	public TriangleNode() {
		value = 0;
		sum = 0;
	}
	public int getValue(){
		return value;
	}	
	public int getSum(){
		return sum;
	}
	public void setLeftChild(TriangleNode leftChild){
		this.leftChild = leftChild;
		
	}
	public void setRightChild(TriangleNode rightChild){
		this.rightChild = rightChild;		
	}
	public TriangleNode getLeftChild(){
		return leftChild;
	}
	public TriangleNode getRightChild(){
		return rightChild;
	}
	public void setSum(int sum){
		this.sum = sum;
	}
	
	//Debug only
	public int getID(){
		return id;
	}	
	//Debug only
	public void setID(int id){	
		this.id = id;
	}
	
}


public class Triangle {
	//This node will serve as our access point into the Triangle and will be the top-most node.
	private TriangleNode head = null;
	private ArrayList<TriangleNode> nodes;
	
	public Triangle(String fileName){
		constructTriangle(fileName);
	}
	
	public TriangleNode constructTriangle(String fileName){
		//This method constructs a Triangle data structure when given a file name containing integer values.
		
		if(head != null){
			return null;			
		}
		
		//Open given file
		File triangleFile = new File(fileName);
		Scanner scanner = new Scanner(System.in);
		
		try{
			scanner = new Scanner(triangleFile);
		} 
		catch (Exception e) {
			e.printStackTrace();
			scanner.close();
			System.out.println(fileName + " not found");
			System.exit(0);
		}
		
		//Place values of file into an ArrayList of TriangleNode's		
		nodes = new ArrayList<TriangleNode>();
		while(scanner.hasNext()){
			nodes.add(new TriangleNode(scanner.nextInt()));
		}
		
		head = nodes.get(0);	
		
		//Create relationships between the nodes.
		
		//Child1 will hold the index of the left child.
		int child1 = 1;
		//Child2 will hold the index of the right child. 
		int child2 = 2;
		//rowIndex will hold the index of the start of the next row.
		int rowIndex = 1;
		//Assists in counting the index of the row by holding the amount of elements in the row.
		int numRowElements = 1;
		//The number node we are on.
		int counter = 0;
		
		//Moves through the ArrayList, assigning children.	
		
		//This will perform for every TriangleNode in nodes.
		while(counter < nodes.size()){
			child1 = rowIndex;
			child2 = child1 + 1;
			//Moves through the row, assigning children
			for(int i = 0; i < numRowElements; i++){
				nodes.get(counter).setID(counter);
				//Ensures that the child is within bounds
				if(child1 < nodes.size()){
					//Assign the child residing at the childs index.
					nodes.get(counter).setLeftChild(nodes.get(child1));
				}
				if(child2 < nodes.size()){
					nodes.get(counter).setRightChild(nodes.get(child2));
				}
				
				//Update the children and current node index
				child1++;
				child2++;
				counter++;
			}
						
			//The row size increases by 1 for every row
			numRowElements++;
			rowIndex += numRowElements;
			
		}
		
		scanner.close();
		return head;
	}
	
	public int getLargestPath(){
		//This method calculates the largest path when moving down the triangle in adjacent nodes.
		//Calculates sums for every node, then returns the sum of the head.
		
		//For every node in the triangle, starting at the last node and moving backwards
		for(int i = nodes.size() - 1; i >= 0; i--){
			//If the node is a leaf, set it's sum equal to its own value
			if(nodes.get(i).getLeftChild() == null && nodes.get(i).getRightChild() == null){
				nodes.get(i).setSum(nodes.get(i).getValue());
			}
			//Otherwise, set it's sum equal to the greater sum of its two children, plus it's own value.
			else{
				int x = nodes.get(i).getLeftChild().getSum();
				if(x >= nodes.get(i).getRightChild().getSum()){
					nodes.get(i).setSum(nodes.get(i).getValue() + x);
				} else {
					nodes.get(i).setSum(nodes.get(i).getValue() + nodes.get(i).getRightChild().getSum());
				}
			}
		}
		//After this is done, the head's sum should hold the largest path.
		return head.getSum();
	}
	
	public TriangleNode getTriangleHead(){
		return head;
	}
	
	public ArrayList<TriangleNode> getNodes(){
		return nodes;
	}
}


