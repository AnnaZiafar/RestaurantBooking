document.addEventListener('click', (e) => {
    const select = document.querySelector('.city-selection');
    const options = document.querySelector('.city-options');
    const cards = document.querySelectorAll('.restaurant-cards');

    if(e.target.closest('.city-selection')) options.classList.toggle('show');
    else(options.classList.remove('show'));

    if(e.target.closest('.city-options div')){
        const selectedCity = e.target.textContent.trim();
        select.textContent = selectedCity;
        options.classList.remove('show')

        cards.forEach(card => {
            const city = card.dataset.city.trim();

            if (selectedCity.toLowerCase() === "Välj en stad" || selectedCity.toLowerCase() === city.toLowerCase()) {
                card.style.display = "block";
            } else {
                card.style.display = "none";
            }
        })
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