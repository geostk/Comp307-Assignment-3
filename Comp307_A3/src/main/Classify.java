package main;

import java.util.ArrayList;

public class Classify {
	
	static Data data;
	static ArrayList<ArrayList<Integer>> classifiedData = new ArrayList<ArrayList<Integer>>();
	static ArrayList<float[]> trueTable = new ArrayList<float[]>();
	static ArrayList<float[]> falseTable = new ArrayList<float[]>();
	
	static void classify() {		
		System.out.println("------- Classified Probabilities of Test Data -------");
		System.out.println("     Spam     -     Not Spam");
		for(int i=0; i<Data.getTestData().size(); i++){
			
			float spam = 0;
			float notSpam = 0;
			
			ArrayList<Integer> test = Data.getTestData().get(i);
			if(test.get(0) == 1)
				spam = trueTable.get(0)[0];
			else
				spam = falseTable.get(0)[0];
			
			for(int j=1; j<test.size(); j++){
				if(test.get(j) == 1)
					spam = spam * trueTable.get(j)[0];
				else
					spam = spam * falseTable.get(j)[0];
			}
			spam = spam * trueTable.get(trueTable.size()-1)[0];
			
			if(test.get(0) == 1)
				notSpam = trueTable.get(0)[1];
			else
				notSpam = falseTable.get(0)[1];
			for(int j=1; j<test.size(); j++){
				if(test.get(j) == 1)
					notSpam = notSpam * trueTable.get(j)[1];
				else
					notSpam = notSpam * falseTable.get(j)[1];
			}
			notSpam = notSpam * falseTable.get(falseTable.size()-1)[1];			
			System.out.println(i+1 + ": " + spam + " - " + notSpam);
			
			int spamOrNotSpam;
			if(spam>notSpam)
				spamOrNotSpam = 1;
			else
				spamOrNotSpam = 0;			
			ArrayList<Integer> classified = new ArrayList<Integer>();			
			for(int x : test){
				classified.add(x);
			}
			classified.add(spamOrNotSpam);			
			classifiedData.add(classified);		
		}
		
		System.out.println("----------- Test Data Classified -------------");
		int i = 1;
		for(ArrayList<Integer> classified : classifiedData){
			String sClass;
			if(classified.get(classified.size()-1) == 1)
				sClass = "SPAM";
			else
				sClass = "Not Spam";
			System.out.println(i + ": " + sClass);
			i++;
		}	
	}
}
