var hostURL = "http://localhost:8080/wsSolver-ROOT/"
$(document).ready(function(){

    $("#btnSolveWordSearch").on("click", function(){
        $.ajax({
            url: hostURL + "solvewordsearch",
            type: "POST",
            data:
                JSON.stringify({
                    WordToFind: $('#tbwordtofind').val().trim(),
                    WordSearch: $('#tbwordsearch').val().trim()
                }),
            accept: "application/json",
            contentType: "application/json",
            dataType:"json",
            success: function(data, status){
                drawSolution(data, $('#tbwordsearch').val().split('\n')[0].replace(/\s/g, '').length);
                console.log("The request was successful");
                if(data == null){
                    console.log("The response was empty.");
                } else {
                    console.log(data);
                }
            }
        });
    });
});


function drawSolution(wordSearchLocations, wordSearchWidth){
    console.log(wordSearchWidth);
    var htmlString="";
    for (i = 0; i < wordSearchLocations.length; i++){
        if (i != 0){
            if (i % wordSearchWidth == 0){
                htmlString += "<br>";
            }
        }
        var spantag = "";
        if(wordSearchLocations[i].isUsedLetter == true){
            spantag = "<span class=\"letter usedletter\">";
        } else {
            spantag = "<span class=\"letter\">";
        }

        htmlString += spantag + wordSearchLocations[i]._letter + "</span>";
    }
    $('#wordSearchSolution').html(htmlString);
}
