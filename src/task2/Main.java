//-----------------------------------------------------
// Title: Main Class (Task2)
// Author: Gökmen ÇAĞLAR
// ID: 12590403284
// Section: 1
// Assignment: 2
// Description: This is the main class that homework specifications asked for.
//-----------------------------------------------------


package task2;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.util.Scanner;



public class Main {

	/**This method is a helper method that takes 
	 * matrix array and index of the zero as parameter. 
	 * Main aim of this method to find the which zero on the matrix
	 * This method helps to create graph

	 * 
	 * @param Matrix 2d array
	 * @param int x
	 * @return  zero number
	 */
	public static int whichZero(int[][] arr ,int x) {
		int zeroCount=0;
		int sayac=0;

		for(int i=0;i<arr.length;i++) {
			for(int t=0;t<arr[0].length;t++) {
				if(x==sayac) {

					return zeroCount;
				}else {
					if(arr[i][t]==0) {
						zeroCount++;

					}
					sayac++;	
				}

			}
		}

		return zeroCount;
	}

	public static void main(String[] args) {


		int[][] matrix=null;
		

		try {
			
			//at the begining I started with reading inputs.
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(System.in));

			// Reading data using readLine
			String addres = reader.readLine();
			addres=addres.trim(); 
			
			Scanner s = null;
			s = new Scanner(new BufferedReader(new FileReader("./"+addres)));
			int collumn=s.nextInt();		
			int row=s.nextInt();
//			System.out.println(row+" "+collumn);
			
			// Based on these inputs I created my matriz
			matrix=new int[row*2][collumn*2];
			
			
			int insideRow=0;
			int jump2Right=0;
			int jump2Down=0;
			int zeroCounter=0;
			
			//At this point I started to create my 2D array
			// based on the backslahes and slashes
			while (s.hasNext())
			{
				jump2Right=0;

				String str = s.next(); 
				// used char array to reach them easily
				char[] myChar = str.toCharArray();

				//with this nested loop I will create my matrix
				for(int t=0;t<myChar.length;t++) {
					
					// If its / it makes the necessary operations
					if(myChar[t]==47) {
						// `/` 
						//System.out.println(jump2Right+" "+jump2Down+"\n");
						matrix[jump2Down][jump2Right+1]=1;
						matrix[jump2Down+1][jump2Right]=1;

						zeroCounter+=2;
						
						// If its \ it makes the necessary operations	
					}else if(myChar[t]==92){

						matrix[jump2Down][jump2Right]=-1;
						matrix[jump2Down+1][jump2Right+1]=-1;
						zeroCounter+=2;
					}
					jump2Right+=2;

				}
				jump2Down+=2;

			}
			int c=0;

			//After counting the zeros and creating the matrix we can start to create our graph
			Graph graph=new Graph(zeroCounter);

			int counter=0;
			int zeros=0;
			
			//These nested for loops checks every direction of these 0 values 
			for(int r=0;r<row*2;r++){
				for(c=0;c<collumn*2;c++){
					//it checks the left
					if(matrix[r][c]==0) {
						
//						if(c!=0) {
//
//							if(0==matrix[r][c-1]){// soluna
//								graph.addEdge(zeros, whichZero(matrix,counter-1));
//								System.out.println("soluna");
//							}
//						}

						//it checks the right
						if(c!=collumn*2-1) {// sağı
							if(0==matrix[r][c+1]){
								graph.addEdge(zeros, whichZero(matrix,counter+1));
								//	System.out.println("sağı");
							}
						}

						//it checks the top
//						if(r!=0) {
//							if(0==matrix[r-1][c]){// üstü
//								graph.addEdge(zeros,whichZero(matrix,counter-collumn*2));
//								System.out.println("üstü");
//							}
//						}

						//it checks the bottom
						if(r!=row*2-1) {// altı
							if(0==matrix[r+1][c]){
								graph.addEdge(zeros, whichZero(matrix,counter+collumn*2));
								//		System.out.println("altı");
							}
						}


						//it checks the left top
//						if(c!=0 && r!=0){//sol üst
//							if(0==matrix[r-1][c-1] &&  !(matrix[r][c-1]==1 && matrix[r-1][c]==1)){
//								graph.addEdge(zeros, whichZero(matrix,counter-collumn*2));
//								System.out.println("sol üst");
//							}
//						}

						//it checks the left bottom
						if(c!=0 && r!=row*2-1){//sol alt
							if(0==matrix[r+1][c-1] &&  matrix[r][c-1]!=-1 && matrix[r+1][c]!=-1){

								graph.addEdge(zeros,whichZero(matrix,counter+collumn*2-1));
								//		System.out.println("sol alt");
							}
						}

						//it checks the rigth top
//						if(c!=collumn*2-1 && r!=0){//sağ üst
//							if(0==matrix[r-1][c+1] &&  !(matrix[r-1][c]==-1 && matrix[r][c+1]==-1)){
//								graph.addEdge(zeros, whichZero(matrix,counter-collumn*2+1));
//								System.out.println("sağ üst");
//							}
//						}
						
						//it checks the right bottom 
						if(c!=collumn*2-1 && r!=row*2-1){//sağ alt
							if(0==matrix[r+1][c+1] &&  matrix[r][c+1]!=1 && matrix[r+1][c]!=1){
								graph.addEdge(zeros,whichZero(matrix,counter+collumn*2+1) );
								//	System.out.println("sağ alt");
							}
						}
						//System.out.println(counter);
						zeros++;

					}
//					System.out.println();
					counter++;
				}
			}

			//after adding all these edges now we can check the cycles.
			Cycle cycle=new Cycle(graph);

			//If there isn't a cycle it prints non
			// else it uses the helps of cycleLengthStorage arraylist to find max value
			if(cycle.isCyclic==false) {
				System.out.println("There are no cycles.");
			}else {
				int max=0;
			for(int i=0;i<cycle.cycleLengthStorage.size();i++) {
				if(max<cycle.cycleLengthStorage.get(i)) {
					max=cycle.cycleLengthStorage.get(i);	
				}
			}

				System.out.println(cycle.cycleCounter+" Cycles; the longest has length "+max+".");
			}
			
		}catch(IOException e) {
			System.out.println("There is no such file.");
			
		}


	}

}
