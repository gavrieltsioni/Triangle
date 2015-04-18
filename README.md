# Triangle 

	This project takes a text file which contains a number triangle and calculates the maximum total when moving from the top of the triangle to the bottom, moving only to adjacent spots and adding up the value of each visited node. Implements a 'Triangle' data structure, which consists of Triangle nodes with children relationships to match the text file.

	This project contains the methods
		1. constructTriangle, which takes a text file and constructs a Triangle data structure based on its contents.
		2. getLargestPath, which calculates the largest path from the highest row to the lowest row. This method works by calculating 'sum' values for the nodes. A 'sum' value of a node is its own value plus the larger sum of either child. A leafs sum is just its own value. The method goes from the end of a row to the start of a row, starting at the lowest row and moving up, calculating the sum value of each node. The sum value stored at the root when this method completes is then the largest path from the top to the bottom. 

	This project was completed in Java and implements a "Triangle" data structure.

-

	Gavriel Tsioni