package songs;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class SongTable {
	public static void main(String[] args) {
		
		try {
			File file = new File("./src/songs/Songs.txt");
			
			Scanner fileIn = new Scanner(file);
			String oldSongs = "";
			int count = 0;
			
			if (fileIn.hasNext()) {
				while(fileIn.hasNext()) {
					oldSongs += fileIn.nextLine() + "\n";
					count++;
				}		
			}
			
			FileWriter writer = new FileWriter(file);
			if (!oldSongs.isEmpty()) {
				writer.write(oldSongs);
				count--;
			}
			writer.flush();
		
			boolean enterAnother;
			Scanner sc = new Scanner(System.in);
			
			do {
				System.out.print("\n" + ++count + " - Enter the song title: ");
				String song = sc.nextLine();
				
				System.out.print("    Enter the artist: ");
				String artist = sc.nextLine();
				
				System.out.print("    Enter the album: ");
				String album = sc.nextLine();
				
				System.out.print("    Enter the song length: ");
				String length = sc.nextLine();
				
				System.out.print("\nEnter another song? (y or n): ");
				
				SongT s = new SongT(song, artist, album, length);
				writer.write(s + "\n");
				writer.flush();
				enterAnother = sc.nextLine().toLowerCase().equals("y");
			} while(enterAnother);
			
			fileIn.close();
			sc.close();
			writer.close();
			
		} catch (Exception e) {
			System.out.println("Something went wrong.");
		}
	}
}

class SongT {
	String title, artist, album, length;
	
	public SongT(String title, String artist, String album, String length) {
		if (title.length() > 30) {
			this.title = title.substring(0,27).concat("...");
		} else {
			this.title = title;
		}
		
		if (artist.length() > 20) {
			this.artist = artist.substring(0,17).concat("...");
		} else {
			this.artist = artist;
		}
		
		if (album.length() > 30) {
			this.album = album.substring(0,27).concat("...");
		} else {
			this.album = album;
		}
		this.length = length;
	}
	
	public String toString() {
		return String.format("%-30s    %-20s    %-30s    %5s", title, artist, album, length);
	}
}
