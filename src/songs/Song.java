package songs;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Song<A, B, C, D> {
	private A songData1;
	private B songData2;
	private C songData3;
	private D songData4;
	
	public Song(A songData1, B songData2, C songData3, D songData4) {
		this.songData1 = songData1;
		this.songData2 = songData2;
		this.songData3 = songData3;
		this.songData4 = songData4;
	}
	
	public Song() {}
	
	public A getSongData1() {
		return songData1;
	}

	public void setSongData1(A songData1) {
		this.songData1 = songData1;
	}

	public B getSongData2() {
		return songData2;
	}

	public void setSongData2(B songData2) {
		this.songData2 = songData2;
	}

	public C getSongData3() {
		return songData3;
	}

	public void setSongData3(C songData3) {
		this.songData3 = songData3;
	}

	public D getSongData4() {
		return songData4;
	}

	public void setSongData4(D songData4) {
		this.songData4 = songData4;
	}

	public static void displayAllSongs(LinkedList<Song> list) {
		Iterator<Song> iterator = list.iterator();
		
		while(iterator.hasNext()) {
			Song s = iterator.next();
			System.out.printf("[songData1=%s, songData2=%s, songData3=%s]\n", s.getSongData1(), s.getSongData2(), s.getSongData3());
		}
	}
	
	public static Song[] readSongsArr() throws IOException {
		LinkedList<Song> songsLL = readSongs(); // calling the other method to get the songs
		Song[] songs = new Song[songsLL.size()];
		
		for (int i=0; i < songs.length; i++) {
			songs[i] = songsLL.pollFirst();
		}
		return songs;
	}
	
	public static LinkedList<Song> readSongs() throws IOException{
		File file = new File("./src/songs/SongWithId.txt");
		Scanner sc = new Scanner(file);
		
		LinkedList<Song> songs = new LinkedList<>();
		while (sc.hasNext()) {
			Song<Integer, String, String, String> song = new Song<>();
			
			// get ID
			song.setSongData1(Integer.parseInt(sc.next())); 
		
			// get title 
			song.setSongData2(sc.next()); 
			
			// get artist (same way as title) 
			song.setSongData3(sc.next()); 
			
			// get album (same way as title) 
			song.setSongData4(sc.next()); 
			
			// skips the song length
			sc.next();
			
			// adds the song to the list 
			songs.add(song);
		}
		sc.close();
		return songs;
	}
	
	public static int searchSongsName(Song[] songs, Song s) {
		for (int i=0; i < songs.length; i++) {
			Object current = songs[i].getSongData2(); // songData2 holds the titles
			if (s.getSongData2().equals(current)) {
				return i;
			}
		}
		return -1;
	}
	
	// using ID because i didn't use a year category
	public static int searchSongsId(Song[] songs, Song s) {
		for (int i=0; i < songs.length; i++) {
			Object current = songs[i].getSongData1(); // songData1 holds the id
			if (s.getSongData1().equals(current)) {
				return i;
			}
		}
		return searchSongsName(songs, s);
	}

	@Override
	public String toString() {
		return "Song [songData1=" + songData1 + ", songData2=" + songData2 + ", songData3=" + songData3 + ", songData4="
				+ songData4 + "]";
	}
	
	public static void main(String[] args) throws IOException {
		Song<Integer, String, String, String> song1 = new Song<>();
		song1.setSongData1(123);
		song1.setSongData2("TestTitle1");
		song1.setSongData3("TestArtist");
		song1.setSongData4("TestAlbum1");
		System.out.println(song1);
		
		Song<String, String, String, String> song2 = new Song<>("TestTitle2", "TestArtist2", "TestAlbum2", "TestLength");
		System.out.printf("\n%s, %s, %s, %s\n\n", song2.getSongData1(), song2.getSongData2(), song2.getSongData3(), song2.getSongData4());
		
		Song<String, String, String, Integer> song3 = new Song<>("t1", "t2", "t3", 4);
		
		LinkedList<Song> songs = new LinkedList<>();
		songs.add(song1);
		songs.add(song2);
		songs.add(song3);
		
		displayAllSongs(songs);
		
		System.out.println("\nLinkedList of songs from file:");
		LinkedList<Song> songsFromFile = readSongs();
		for (Song s : songsFromFile) System.out.println(s);
		
		System.out.println("\nArray of songs from file:");
		Song<Integer, String, String, String>[] songsFromFileArr = readSongsArr();
		for (Song s : songsFromFileArr) System.out.println(s);
		
		Song test1 = new Song<>(8225126, "Amoeba", "Clairo", "4Ever");
		Song test2 = new Song<>(1234567, "Amoeba", "Clairo", "4Ever");
		
		System.out.println("\nSearching for songs: ");
		System.out.println(searchSongsName(songsFromFileArr, test1));
		System.out.println(searchSongsId(songsFromFileArr, test2));
		
		Comparator<Song<Integer, String, String, String>> titleComparator = 
				Comparator.comparing(Song::getSongData2, (s1, s2) -> s1.compareTo(s2));
		
		Comparator<Song<Integer, String, String, String>> idComparator = 
				Comparator.comparing(Song::getSongData1, (s1, s2) -> s1.compareTo(s2));
		
		System.out.println("\nSongs sorted by Id, then title");
		Arrays.sort(songsFromFileArr, idComparator.thenComparing(titleComparator));
		for (Song s : songsFromFileArr) System.out.println(s);
	}
}

