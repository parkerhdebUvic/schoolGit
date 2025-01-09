function displayResults(data) {
	
	/* this should be fixed so that it actually */
	/* outputs the title of the movie the user  */
	/* searchs for (enters into the input box)  */
	let title = "Lab 8";
	
	$("#resultTitle").html(title);
}




function submit() {
	$("#output").show();
    let baseURL = "https://www.omdbapi.com/?";
    let key = "3896198"
    let title = $("#movieTitle").val();
    let year = $("#movieYear").val();

    let finalURL = baseURL + "apikey=" + key + "&t=" + title + "&y" + year;

    $.get(finalURL, function(data){
        document.getElementById("raw").innerHTML = JSON.stringify(data);
        displayResults(data);
    });
}

