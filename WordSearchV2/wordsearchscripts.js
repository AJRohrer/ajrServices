var hostURL = "http://localhost:8080/wsSolver-ROOT/";
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


function drawSolution(data, wordSearchWidth){
    var htmlString="";
    for (i = 0; i < data.finalSolvedWordSearch.length; i++){
        if (i != 0){
            if (i % wordSearchWidth == 0){
                htmlString += "<br>";
            }
        }
        var spantag = "";
        if(data.finalSolvedWordSearch[i].isUsedLetter == true){
            spantag = "<span class=\"letter usedletter\">";
        } else {
            spantag = "<span class=\"letter\">";
        }

        htmlString += spantag + data.finalSolvedWordSearch[i]._letter + "</span>";
    }
    $('#wordSearchSolution').html(htmlString);
    $('#wordCount').html($('#tbwordtofind').val() + " count: " + data.numberOfWords);
}
