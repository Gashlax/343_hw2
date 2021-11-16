//-----------------------------------------------------
// Title: Main Class (Task1)
// Author: Gökmen ÇAĞLAR
// ID: 12590403284
// Section: 1
// Assignment: 2
// Description: This is the main class that homework specifications asked for.
//-----------------------------------------------------


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int src = 0;
		int dest= 0;
		int inputLength=0;
		int forbidens[] = null;
		
		
		// We take the data from user
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(System.in));

			// Reading data using readLine
			String addres = reader.readLine();
			addres=addres.trim(); 
			
			File myObj = new File("./"+addres);
			Scanner myReader1 = new Scanner(myObj);

			//after reading it I used replaceAll method to trim spaces.
			for(int t=0;t<2;t++) {
				String input = myReader1.nextLine();

				int sourdest=Integer.parseInt(input.replaceAll(" ",""));
				if(t==0) {
					src=sourdest;
				}else {
					dest=sourdest;
				}
			}


			String length1 = myReader1.nextLine();
			inputLength=Integer.parseInt(length1);

			forbidens=new int[inputLength];

			// To read forbidens
			for(int t=0;t<inputLength;t++) {
				String forb = myReader1.nextLine();
				int forbi=Integer.parseInt(forb.replaceAll(" ",""));
				forbidens[t]=forbi;
				//System.out.println(forbidens[t]);
			}

		}catch(IOException e) {
			System.out.println("An error occurred.");
			System.out.println("There is no such a addres");
			return;
		}



		Graph graph=new Graph(10001);
		//To create graph I use Graph.java helper methods.

		for(int i=0; i<10000;i++) {

				int birler=graph.increaseFirst(i);
				int onlar=graph.increaseSecond(i);
				int yuzler=graph.increaseThird(i);
				int binler=graph.increaseForth(i);

				graph.addEdge(i, birler);
				graph.addEdge(i, onlar);
				graph.addEdge(i, yuzler);
				graph.addEdge(i, binler);
			
		}

		//After creating the graph I use bfs class to calculate shortest path
		BreadthFirstPaths bfs = new BreadthFirstPaths(graph, src,forbidens);
		
		// based on the bfs has Path to method(it checks marked array) I used if statment 
		if (bfs.hasPathTo(dest)) {
			// bfs dist to return MAX_VALUE integer if it cannot find it so I checked it.
			if(Integer.MAX_VALUE!=bfs.distTo(dest)) {
				System.out.println(bfs.distTo(dest));
			}else {
				System.out.println("-1");
			}
			
		}else {
			System.out.println("-1");
		}
	}


	


}
