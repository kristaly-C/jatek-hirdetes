const select = document.getElementById('game');
const info = document.getElementById('gameInfo');

select.addEventListener('change', (ev) =>{
    console.log(ev.target.value);
    if (info.classList.contains('hide')){
        info.classList.remove('hide');
    }
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function (){
        document.querySelector('.pic img').setAttribute('src',
            this.responseText);
    }
    xhttp.open("GET","/api/picfor/"+ev.target.value);
    xhttp.send();
    }
)



