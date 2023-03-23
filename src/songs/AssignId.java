package songs;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class AssignId {
	public static void main(String[] args) {
		try {
			File inFile = new File("./src/songs/Songs.txt");
			File outFile = new File("./src/songs/SongWithId.txt");
			
			Scanner sc = new Scanner(inFile);
			FileWriter writer = new FileWriter(outFile);
			
			while(sc.hasNext()) {
				String line = sc.nextLine();
				int id = (int)(Math.random()*9000000+1000000);
				writer.write(String.format("%d    %s\n", id, line));
			}

			sc.close();
			writer.close();
			
			System.out.println("Complete.");
		} catch(Exception e) {
			System.out.println("\nSomething went wrong.");
		}
	}
}
