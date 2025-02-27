const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))

function show_error(msg){
    /*
    */
    ep = document.getElementById("error_panel_p");
    ep.innerHTML = msg;
    console.warn(msg);
    setTimeout(() => {
         ep.innerHTML = "";
         ep.style.visibility = 'hidden';
       }, 5000);
}

function async_call(url, domid, append = false){
    /*
    */
    fetch(url).then(function (response) {
        return response.text();
    }).then(function (html) {
        console.log(html)
        if(append)
            document.getElementById(domid).innerHTML += html;
        else
            document.getElementById(domid).innerHTML = html;
    }).catch(function (err) {
       show_error('Something went wrong while getting the login form.'+ err);
    });
}


function check_login(){
     /*
    */
    id = document.getElementById("uid").value;
    fetch("checklogin").then(function (html) {
        alert('response session ' + sessionStorage.getItem('uid'));
        if(sessionStorage.getItem('uid') == id){
            location.href = 'index.html';
        }
        else{
            show_error('Wrong credentials');
        }
    }).catch(function (err) {
       show_error('Error while checking credentials : '+ err);
    });
}
