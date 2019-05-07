hostURL = "http://localhost:8080/"
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
