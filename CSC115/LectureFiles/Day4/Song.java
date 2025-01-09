// Song.java

public class Song {
	// 1. generalize what you use to describe a song?
	//    these dictate the fields/attributes in our Song class
	private String title;
	private String artist;
	// 2. create fields in Song class
	public Song() {
		title = "unnamed";
		artist = "unknown";
	}

	public Song(String t) {
		title = t;
		artist = "unknown";
	}

	public Song(String t, String a) {
		title = t;
		artist = a;
	}

	public String getArtist(){
		return artist;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String updatedTitle){
		title = updatedTitle;
	}

	public String toString() {
		return title + " by " + artist;
	}

	// 3. go to tester and create some song objects...
	
	// 4. change fields to private in Song class


	// 5. add getters and setters for fields

	// 6. how do we initialize a song with data upon creation?
	//  - add a custom constructor

	// 7. add a toString method
	//  - takes no arguments and returns a String
	//    representing this object
   
}