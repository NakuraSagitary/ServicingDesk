function Login()     {

    var urlLogin = "https://jira.atlassian.com/rest/api/latest/issue/JRA-9";
    // var de user    
    var user = $("user").val();
    // var de senha
    var password = $("senha").val();

    $.ajax({
        url : urlLogin,
        type : "post",
        datatype : "json",
        sucess : function(val){
            console.log(val);
        },
        error : function(erro){
        console.log(erro);
        }
    })

}

/*
HTML
<button onclick="Login()">Login</button>
*/

