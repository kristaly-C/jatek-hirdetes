/*const delbutton = document.getElementById("dltbtn");
const id = delbutton.parentElement.firstElementChild.firstElementChild.innerHTML;

delbutton.addEventListener("click",(ev => {
    var url = "/api/AdDelete/";
    const xhttp = new XMLHttpRequest();

    xhttp.open("GET", url+id, true);
    xhttp.onload = function (){
        if (xhttp.readyState === xhttp.DONE){
            if(xhttp.status === 201){
                location.reload();
            }else {
                alert((xhttp.response));
            }
        }

    }
    xhttp.send(null);

}))

 */

function delfunc(clicked_id){
    let result = clicked_id.substring(3);
    console.log(result);

    var url = "/api/AdDelete/";
    const xhttp = new XMLHttpRequest();

    xhttp.open("GET", url+result, true);
    xhttp.onload = function (){
        if (xhttp.readyState === xhttp.DONE){
            if(xhttp.status === 201){
                location.reload();
            }else {
                alert((xhttp.response));
            }
        }

    }
    xhttp.send(null);
}