const select = document.getElementById('game');
const info = document.getElementById('gameInfo');

select.addEventListener('change', (ev) =>{
    console.log(ev.target.value);
    if (info.classList.contains('hide')){
       //info.classList.remove('hide');
    }else {
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function (){
            document.querySelector('.pic img').setAttribute('src',
                this.responseText);
        }
        xhttp.open("GET","/api/picfor/"+ev.target.value);
        xhttp.send();
    }
    }
)


const addPlatform = document.querySelector('.newgame h5');
const firstBlock = document.getElementById('mainBlock');

addPlatform.addEventListener("click",(ev) =>{
    if (info.classList.contains('hide')){
        info.classList.remove('hide');
        firstBlock.classList.add('animation');
    }
})

const searchBtn = document.getElementById('sbtn');
const newGameName = document.getElementById('searchedGameName');

searchBtn.addEventListener("click",(ev) =>{
    console.log(newGameName.value);

    const xhttp = new XMLHttpRequest();
    xhttp.onload = function (){
        if (xhttp.readyState === xhttp.DONE){
            if(xhttp.status === 201){
                location.reload();
            }else {
                alert((xhttp.response));
            }
        }

    }
    xhttp.open("GET","/api/newgame/"+newGameName.value);
    xhttp.send(null);
    newGameName.value = '';
})


function validateForm(){
    let x = document.forms["newAd"]["gameprice"].value;
    if(x == ""){
        alert("Nem adtál meg árat!");
        return false;
    }
}



