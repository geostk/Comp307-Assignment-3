package main;

import java.util.ArrayList;

public class Table {
	
public static void createTables(){
		
		ArrayList<int[]> hasFeature = new ArrayList<int[]>();
		ArrayList<int[]> noFeature = new ArrayList<int[]>();
		
		for(int i=0; i<Classify.data.getTrainingData().get(0).size()-1; i++){
			
			int hasSpamWord = 1; 
			int hasNoSpamWord = 1; 
			int noWordIsSpam = 1; 
			int noWordNotSpam = 1; 
			
			for(ArrayList<Integer> instance : Classify.data.getTrainingData()){
				
				if(instance.get(i) == 1){
					
					if(instance.get(instance.size()-1) == 1)
						hasSpamWord++;
					else
						hasNoSpamWord++;
				}
				else{
					
					if(instance.get(instance.size()-1) == 1)
						noWordIsSpam++;
					else
						noWordNotSpam++;
				}
			}
			
			int[] True = {hasSpamWord,hasNoSpamWord};
			int[] False = {noWordIsSpam,noWordNotSpam};
			hasFeature.add(True);
			noFeature.add(False);
		}
		
		int spam = 1;
		int notSpam = 1; 
		
		for(ArrayList<Integer> instance : Classify.data.getTrainingData()){
			if(instance.get(instance.size()-1) == 1)
				spam++;
			else
				notSpam++;
		}
		
		int[] spamOccurrence = {spam,notSpam};
		int count = 1;
		
		hasFeature.add(spamOccurrence);
		noFeature.add(spamOccurrence);
	
		System.out.println("------------------ Occurrence Table ------------------");
		for(int i=0; i<hasFeature.size()-1; i++){
			System.out.println(count + ": " + hasFeature.get(i)[0] + " - " + hasFeature.get(i)[1]);
			count++;
			System.out.println(count + ": " + noFeature.get(i)[0] + " - " + noFeature.get(i)[1]);
			count++;
		}		
		
		int size = hasFeature.size()-1;
		
		System.out.println(count + ": " + hasFeature.get(size)[0] + " - " + hasFeature.get(size)[1]);

		float hasWordDenominator = hasFeature.get(hasFeature.size()-1)[0]+1;
		float noWordDenominator = noFeature.get(noFeature.size()-1)[1]+1;
		float total = hasWordDenominator + noWordDenominator;
		
		for(int i=0; i<hasFeature.size()-1; i++){
			float t = hasFeature.get(i)[0];
			float f = hasFeature.get(i)[1];			
			float tProb = t/hasWordDenominator;
			float fProb = f/noWordDenominator;			
			float[] probability = {tProb,fProb};
			Classify.trueTable.add(probability);
		}		
		for(int i=0; i<noFeature.size()-1; i++){
			float spamProb = noFeature.get(i)[0];
			float noSpamProb = noFeature.get(i)[1];
			spamProb = spamProb/hasWordDenominator;
			noSpamProb = noSpamProb/noWordDenominator;
			float[] probability = {spamProb,noSpamProb};
			Classify.falseTable.add(probability);
		}		
		float spamT = hasFeature.get(hasFeature.size()-1)[0];
		float noSpamT = hasFeature.get(hasFeature.size()-1)[1];
		float spamProb = spamT/total;
		float noSpamProb = noSpamT/total;
		float[] prob = {spamProb,noSpamProb};
		Classify.trueTable.add(prob);
		Classify.falseTable.add(prob);
		count = 1;
		
		System.out.println("---------- Probability of Occurrences Table ------------");
		for(int i=0; i<hasFeature.size()-1; i++){
			System.out.println(count + ": " + Classify.trueTable.get(i)[0] + " - " + Classify.trueTable.get(i)[1]);
			count++;
			System.out.println(count + ": " + Classify.falseTable.get(i)[0] + " - " + Classify.falseTable.get(i)[1]);
			count++;
		}		
		System.out.println(count + ": " + Classify.trueTable.get(size)[0] + " - " + Classify.trueTable.get(size)[1]);
	}	
}
