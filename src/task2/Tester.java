//-----------------------------------------------------
// Title: Tester Class (Task2)
// Author: Gökmen ÇAĞLAR
// ID: 12590403284
// Section: 1
// Assignment: 2
// Description: This is the tester class that I make my tests. So I didn't deleted my prints
//-----------------------------------------------------


//-----------------------------------------------------
// I made my tests in this class It additionaly prints the matrix
// It helps to trace the adj vertecies. 
//-----------------------------------------------------


package task2;


import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.util.Scanner;





public class Tester {


	public static int whichZero(int[][] arr ,int x) {
		int zeroCount=0;
		int sayac=0;
//		System.out.println(x+" sayac");

		for(int i=0;i<arr.length;i++) {
			for(int t=0;t<arr[0].length;t++) {
				if(x==sayac) {
//					System.out.println(" zero :"+zeroCount);
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
		// TODO Auto-generated method stub

		int[][] matrix=null;


		try {
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
			matrix=new int[row*2][collumn*2];
			int insideRow=0;
			int jump2Right=0;
			int jump2Down=0;
			int zeroCounter=0;
			while (s.hasNext())
			{
				jump2Right=0;
//				System.out.println();
				String str = s.next(); 
				//System.out.println(str);
				char[] myChar = str.toCharArray();

				for(int t=0;t<myChar.length;t++) {
					//System.out.print(myChar[t]);
					if(myChar[t]==47) {
						// `/` 
						//System.out.println(jump2Right+" "+jump2Down+"\n");
						matrix[jump2Down][jump2Right+1]=1;
						matrix[jump2Down+1][jump2Right]=1;

						zeroCounter+=2;

					}else if(myChar[t]==92){
						//System.out.println(jump2Right+" "+jump2Down+"\n");
						matrix[jump2Down][jump2Right]=-1;

						matrix[jump2Down+1][jump2Right+1]=-1;
						zeroCounter+=2;
					}
					jump2Right+=2;

				}
				jump2Down+=2;
				//System.out.println();

//				System.out.println();
			}
			int c=0;
//			System.out.println(row+" "+collumn);
			for(int m=0;m<(row*2);m++) {
				for(int y=0;y<(collumn*2);y++) {
						System.out.print(matrix[m][y]+" ");

				}
				System.out.println();
			}
			System.out.println();
			Graph graph=new Graph(zeroCounter);

			int counter=0;
			int zeros=0;
			for(int r=0;r<row*2;r++){
				for(c=0;c<collumn*2;c++){
					if(matrix[r][c]==0) {
						//System.out.print(zeros+" counter ");
//						if(c!=0) {
//
//							if(0==matrix[r][c-1]){// soluna
//								graph.addEdge(zeros, whichZero(matrix,counter-1));
//								System.out.println("soluna");
//							}
//						}

						if(c!=collumn*2-1) {// sağı
							if(0==matrix[r][c+1]){
								graph.addEdge(zeros, whichZero(matrix,counter+1));
								//	System.out.println("sağı");
							}
						}


//						if(r!=0) {
//							if(0==matrix[r-1][c]){// üstü
//								graph.addEdge(zeros,whichZero(matrix,counter-collumn*2));
//								System.out.println("üstü");
//							}
//						}

						if(r!=row*2-1) {// altı
							if(0==matrix[r+1][c]){
								graph.addEdge(zeros, whichZero(matrix,counter+collumn*2));
								//		System.out.println("altı");
							}
						}



//						if(c!=0 && r!=0){//sol üst
//							if(0==matrix[r-1][c-1] &&  !(matrix[r][c-1]==1 && matrix[r-1][c]==1)){
//								graph.addEdge(zeros, whichZero(matrix,counter-collumn*2));
//								System.out.println("sol üst");
//							}
//						}


						if(c!=0 && r!=row*2-1){//sol alt
							if(0==matrix[r+1][c-1] &&  matrix[r][c-1]!=-1 && matrix[r+1][c]!=-1){

								graph.addEdge(zeros,whichZero(matrix,counter+collumn*2-1));
								//		System.out.println("sol alt");
							}
						}

//						if(c!=collumn*2-1 && r!=0){//sağ üst
//							if(0==matrix[r-1][c+1] &&  !(matrix[r-1][c]==-1 && matrix[r][c+1]==-1)){
//								graph.addEdge(zeros, whichZero(matrix,counter-collumn*2+1));
//								System.out.println("sağ üst");
//							}
//						}

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
			
			
			int i=0;
			for(int v=0;v<graph.V();v++) {
				for(int w:graph.adj(v)) {
					System.out.println(v+" "+w);
				}
			}

			Cycle cycle=new Cycle(graph);
			System.out.println("_______________________________________________________________________");
			System.out.println(cycle.counter+" counter");
			System.out.println(cycle.cycleCounter+" cycle counter");
			System.out.println(cycle.hasCycle());
		
			
	

			System.out.println("_______________________________________________________________________");
			
			
			if(cycle.isCyclic==false) {
				System.out.println("There are no cycles.");
			}else {
				int max=0;
			for(i=0;i<cycle.cycleLengthStorage.size();i++) {
				if(max<cycle.cycleLengthStorage.get(i)) {
					max=cycle.cycleLengthStorage.get(i);	
				}
			}
				
				
				System.out.println(cycle.cycleCounter+" Cycles; the longest has length "+max+".");
			}
			
		}catch(IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}


	}

}
