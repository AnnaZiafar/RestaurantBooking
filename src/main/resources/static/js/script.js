const select = document.querySelector('.city-selection');
const options = document.querySelector('.city-options');

document.addEventListener('click', (e) => {
    if(e.target.closest('.city-selection')) options.classList.toggle('show');
    else(options.classList.remove('show'));

    if(e.target.closest('.city-options div')){
        select.textContent = e.target.textContent;
        options.classList.remove('show')
    }

})