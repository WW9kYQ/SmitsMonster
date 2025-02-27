function getLoginForm (){
    fetch('login').then(function (response) {
        return response.text();
    }).then(function (html) {
        console.log(html)
        document.getElementById("central_content_div").innerHTML += html;
    }).catch(function (err) {
        console.warn('Something went wrong while getting the login form.', err);
    });
}
