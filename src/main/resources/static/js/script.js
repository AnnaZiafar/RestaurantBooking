document.addEventListener('click', (e) => {
    const select = document.querySelector('.city-selection');
    const options = document.querySelector('.city-options');

    if(e.target.closest('.city-selection')) options.classList.toggle('show');
    else(options.classList.remove('show'));

    if(e.target.closest('.city-options div')){
        select.textContent = e.target.textContent;
        options.classList.remove('show')
    }
})

function loadHeader(){
    fetch('/get-header')
        .then(response => response.text())
        .then(data =>
            document.getElementById('header-placeholder').innerHTML = data)
        .catch(error => console.error('Could not load header', error))
}

globalThis.addEventListener('DOMContentLoaded', loadHeader);