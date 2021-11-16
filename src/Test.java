//-----------------------------------------------------
// Title: Test Class (Task1)
// Author: Gökmen ÇAĞLAR
// ID: 12590403284
// Section: 1
// Assignment: 2
// Description: This is the tester class In this class I used another graph implementation
// way to avoid forbidden inputs
//-----------------------------------------------------

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//-----------------------------------------------------
// This class has a huge difference than Main.java 
// In this class I tested my another solution technique but it DOES NOT WORK
// So, plus do not test this class.
//-----------------------------------------------------

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
	
		int src = 0;
		int dest= 0;
		int inputLength=0;
		int forbidens[] = null;

		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(System.in));

			// Reading data using readLine
			String addres = reader.readLine();
			addres=addres.trim(); 
			File myObj = new File("./"+addres);
			Scanner myReader1 = new Scanner(myObj);


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


			for(int t=0;t<inputLength;t++) {
				String forb = myReader1.nextLine();
				int forbi=Integer.parseInt(forb.replaceAll(" ",""));
				forbidens[t]=forbi;
				//System.out.println(forbidens[t]);
			}

		}catch(IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}



		Graph graph=new Graph(10001);
		int nexts[]=new int[4];
		boolean isForbiden=false;

		for(int i=0; i<10000;i++) {
			for(int forInts=0;forInts<forbidens.length;forInts++) {
				if(i==forbidens[forInts]) {
					isForbiden=true;
				}
			}
			
			//----------------------------------------------------------------------------------
			//This is  different the main difference from the MAIN.java class but it does not work I 
			// wanted
			//----------------------------------------------------------------------------------
			if(!isForbiden) {
				
				int birler=graph.increaseFirst(i);
				int onlar=graph.increaseSecond(i);
				int yuzler=graph.increaseThird(i);
				int binler=graph.increaseForth(i);
				nexts[0]=birler;
				nexts[1]=onlar;
				nexts[2]=yuzler;
				nexts[3]=binler;
				isForbiden=false;
				for(int m=0;m<nexts.length;m++) {
					for(int k=0;k<forbidens.length;k++) {
						if(nexts[m]==forbidens[k]) {
							isForbiden=true;
						}
					}
					graph.addEdge(i, nexts[m]);
				}

			}
//				graph.addEdge(i, birler);
//				graph.addEdge(i, onlar);
//				graph.addEdge(i, yuzler);
//				graph.addEdge(i, binler);
			
		}


		BreadthFirstPaths bfs = new BreadthFirstPaths(graph, src,forbidens);

		if (bfs.hasPathTo(dest)) {
			if(Integer.MAX_VALUE!=bfs.distTo(dest)) {
				System.out.println(bfs.distTo(dest));
			}else {
				System.out.println("-1");
			}
			
		}else {
			System.out.println("-1");
		}
	}


	//	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	//		try {
	//			System.out.println("Please input an integer");
	//			for(int t=0;t<2;t++) {
	//				String input = br.readLine();
	//				
	//				int sourdest=Integer.parseInt(input.replaceAll(" ",""));
	//				if(t==0) {
	//					src=sourdest;
	//				}else {
	//					dest=sourdest;
	//				}
	//			}
	//			String length1 = br.readLine();
	//			int length=Integer.parseInt(length1);
	//			int forbidens[]=new int[length];
	//			for(int t=0;t<length;t++) {
	//				String forb = br.readLine();
	//				int forbi=Integer.parseInt(forb.replaceAll(" ",""));
	//				forbidens[t]=forbi;
	//				//System.out.println(forbidens[t]);
	//			}
	//
	//		}catch(IOException e) {
	//			System.out.println("An error occurred.");
	//			e.printStackTrace();
	//		}


	//		try {
	//			File myObj = new File("input.txt");
	//			Scanner myReader1 = new Scanner(myObj);
	//			int src1 = myReader1.nextInt();
	//			String dest1 = myReader1.nextLine();
	//			String length1 = myReader1.nextLine();
	//			int length=Integer.parseInt(length1);
	//			int src=Integer.parseInt(src1);
	//			int dest=Integer.parseInt(dest1);
	//			int forbidens[]=new int[length];
	//
	//			
	//			for(int t=0;t<length;t++) {
	//				String forb = myReader1.nextLine();
	//				int forbiden=Integer.parseInt(forb);
	//				forbidens[t]=forbiden;
	//			}
	//			
	//			}catch(FileNotFoundException e) {
	//				System.out.println("An error occurred.");
	//				e.printStackTrace();
	//			}
	//		



}
