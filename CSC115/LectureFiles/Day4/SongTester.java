// SongTester.java
//
// We will write code here to test our implementation of Song.java
//
// We have put this code in a different file to help you understand 
// the difference between static and non-static.  In the real world,
// this style of test code might be included in the Song.java file.

public class SongTester {
    
	public static void main (String[] args) {
		/* Recall: if I want to declare an integer: */
		int i; // what is it initialized to?

		/* if I want to declare an array of integers: */
		int[] array;
		
		/* what is it initialized to?
		   how would I initialize it to point to an array of size 10? */

		// int[] array = new int[5];
		// int[] array2 = {7,2,3};

		/* 3a. declare and initialize two new blank songs */
		Song s0 = new Song();
		Song s1 = new Song("Africa", "Toto");
		Song s3 = new Song("Happy Birthday");


		/* 3b. print the values of the fields for each song */
		// doesn't work when fields are set to private
		// System.out.println("s0 artist "+s0.artist+ " title: "+s0.title);
		// System.out.println("s0 artist "+s1.artist+ " title: "+s1.title);
		System.out.println("s0 artist "+s0.getArtist()+ " title: "+s0.getTitle());
		System.out.println("s1 artist "+s1.getArtist()+ " title: "+s1.getTitle());

		/* 3c. set the fields in the song to some values and reprint */
		s1.setTitle("Testing");
		System.out.println("s1 artist: "+s1.getArtist()+ " title: "+s1.getTitle());
		
		/* 5a. update statements where necessary from 3b and 3c */
		System.out.println(s1.toString());
		/* 6a. call constructor with args, then print fields */




		/* 6b. call alternative constructor, then print fields */





		/* 6c. try to print one of the songs
		   ie. pass your Song variable to System.out.println() */


		/* 7a. recompile and run your program - what changes in 6c. */
	}
}