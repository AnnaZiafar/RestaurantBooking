const select = document.querySelector('.city-selection');
const options = document.querySelector('.city-options');
let selectedCity = null;

/**
 * Handles city selection and filtering of restaurant cards.
 * Listens for clicks to toggle the visibility of the dropdown menu and updates
 * the visibility of .restaurant-cards based on the selected data-city attribute.
 * @listens document:click
 */
document.addEventListener('click', (e) => {
    const cards = document.querySelectorAll('.restaurant-cards');

    if(e.target.closest('.city-selection')) options.classList.toggle('show');
    else(options.classList.remove('show'));

    if(e.target.closest('.city-options div')){
        selectedCity = e.target.textContent.trim();
        select.textContent = selectedCity;
        options.classList.remove('show')

        cards.forEach(card => {
            const city = card.dataset.city.trim();

            if (selectedCity === "Alla städer" || selectedCity.toLowerCase() === city.toLowerCase()) {
                card.style.display = "block";
            } else {
                card.style.display = "none";
            }
        })
    }
})

/**
 * Handles searching/filtering of restaurants based on name or category.
 * Listens for both button clicks and Enter key presses.
 */
function setupSearch(){
    const input = document.getElementById('restaurant-search-input');
    const searchContainer = document.getElementById('search-container');
    const cards = document.querySelectorAll('.restaurant-cards');

    const performSearch = (e) => {
        if (e.type === 'submit') e.preventDefault()
        const query = input.value.trim().toLowerCase();

        cards.forEach(card => {
            const restaurantName = card.querySelector('.restaurant-card-info span').textContent.trim().toLowerCase()
            const city = card.dataset.city.trim();

            if(restaurantName.includes(query) && selectedCity === city || selectedCity === 'Alla städer')
                card.style.display = 'block';
            else
                card.style.display = 'none';
        })
    }

    if(searchContainer) searchContainer.addEventListener('submit', performSearch)
}

document.addEventListener('DOMContentLoaded', setupSearch);