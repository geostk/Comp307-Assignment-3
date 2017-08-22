package main;

public class Main {
	
	public static void main(String[] args) {
		Classify.data = new Data("spamLabelled.dat","spamUnlabelled.dat");
		Table.createTables();
		Classify.classify();
	}
	
}
