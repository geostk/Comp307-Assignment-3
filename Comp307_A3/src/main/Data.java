
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {
	private static String trainingFile;
	private static String testFile;
	
	private static ArrayList<ArrayList<Integer>> trainingData = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<ArrayList<Integer>> testData = new ArrayList<ArrayList<Integer>>();
	
	public Data(String trainingFile, String testFile){
		this.trainingFile = trainingFile;
		this.testFile = testFile;
		readFile();
	}
	
	public static void readFile(){
		try{
			
			Scanner trainingScanner = new Scanner(new File(trainingFile));
			
			while(trainingScanner.hasNext()){
				ArrayList<Integer> instance = new ArrayList<Integer>();
				for(int i = 0; i < 13; i++){
					instance.add(Integer.parseInt(trainingScanner.next()));
				}
				getTrainingData().add(instance);
			}
			
			Scanner testScanner = new Scanner(new File(testFile));
			
			while(testScanner.hasNext()){
				ArrayList<Integer> instance = new ArrayList<Integer>();
				for(int i = 0; i < 12; i++){
					instance.add(Integer.parseInt(testScanner.next()));
				}
				getTestData().add(instance);
			}			
			trainingScanner.close();
			testScanner.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

	public static ArrayList<ArrayList<Integer>> getTrainingData() {
		return trainingData;
	}

	public static void setTrainingData(ArrayList<ArrayList<Integer>> trainingData) {
		Data.trainingData = trainingData;
	}

	public static ArrayList<ArrayList<Integer>> getTestData() {
		return testData;
	}

	public static void setTestData(ArrayList<ArrayList<Integer>> testData) {
		Data.testData = testData;
	}
}
