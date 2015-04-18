/*
 *  Author: Gavriel Tsioni 
 * 
 */
public class TriangleTester {
	public static void main(String[] args){
		String fileName = "triangle.txt";
		Triangle triangle = new Triangle(fileName);
		int largestPath = triangle.getLargestPath();
		System.out.println(largestPath);		
	}
}
