function docReady(fn) {
    // see if DOM is already available
    if (document.readyState === "complete" || document.readyState === "interactive") {
        // call on next available tick
        setTimeout(fn, 1);
    } else {
        document.addEventListener("DOMContentLoaded", fn);
    }
}
    const myElement = document.getElementById('lista');
    const ker = document.getElementById('#sea');

    function kereso(){
        var kiv = document.getElementById('sea');
        var nev = kiv.value.toLowerCase();
        console.log(nev);
        for (let i = 0; i < myElement.children.length; i++) {
            if (!myElement.children[i].children[1].children[0].textContent.toLowerCase().match(nev)){
                myElement.children[i].classList.add("hide");

            }else {

                if (myElement.children[i].classList.contains('hide')){
                    myElement.children[i].classList.remove('hide');
                }

            }
        }
    }





window.onscroll = function() {myFunction()};

var navbar = document.getElementById("navbar");
var sticky = navbar.offsetTop;

function myFunction() {
    if (window.pageYOffset >= sticky) {
        navbar.classList.add("sticky")
    } else {
        navbar.classList.remove("sticky");
    }
}