const select = document.querySelector('.city-selection');
const options = document.querySelector('.city-options');
let selectedCity = "Alla städer";
let selectedCategory = "Alla kategorier";

/**
 * Filters the displayed restaurant cards based on the search query and the selected city.
 * It combines the text input from the search bar with the current value of the
 * selectedCity variable to determine which cards should be visible.
 */
function filterRestaurants() {
    const searchInput = document.getElementById('restaurant-search-input');
    const query = searchInput ? searchInput.value.trim().toLowerCase() : "";
    const cards = document.querySelectorAll('.restaurant-cards');

    cards.forEach(card => {
        const restaurantName = card.querySelector('.restaurant-card-info span').textContent.trim().toLowerCase();
        const city = card.dataset.city.trim().toLowerCase();
        const category = card.dataset.category.trim().toLowerCase();

        const currentCity = selectedCity.toLowerCase();
        const currentCategory = selectedCategory.toLowerCase();

        const matchesSearch = restaurantName.includes(query);
        const matchesCity = currentCity === "alla städer" || currentCity === city;
        const matchesCategory = currentCategory === "alla kategorier" || currentCategory === category;

        let visible = false;
        if(query.length > 0){
            visible = matchesSearch && matchesCity;
        } else {
            visible = matchesCategory && matchesCity;
        }

        card.style.display = visible ? "block" : "none";
    });
}

/**
 * Global click event listener to handle custom dropdown behavior.
 * Toggles the visibility of the city options menu when clicking the selection box.
 * Closes the menu when clicking outside the dropdown or when a city has been chosen.
 * Updates the selected city and triggers a re-filter of restaurants when an option is selected.
 *
 * @listens document:click
 */
document.addEventListener('click', (e) => {
    if(e.target.closest('.city-selection')) {
        options.classList.toggle('show');
    } else {
        options.classList.remove('show');
    }

    if(e.target.closest('.city-options div')){
        selectedCity = e.target.textContent.trim();
        select.textContent = selectedCity;
        options.classList.remove('show');

        filterRestaurants();
    }
});

/**
 * Global click event listener for category selection.
 * Checks if the clicked element is, or is contained within, a '.category-cards' element.
 * If a match is found, it extracts the category name, updates the global state,
 * and triggers the restaurant filtering logic.
 *
 * @listens document:click
 */
document.addEventListener('click', (e) => {
    const clickedCard = e.target.closest('.category-cards');

    if(clickedCard){
        selectedCategory = clickedCard.textContent.trim();
        filterRestaurants();
    }
});

function setupSearch(){
    const searchContainer = document.getElementById('search-container');

    if(searchContainer) {
        searchContainer.addEventListener('submit', (e) => {
            e.preventDefault();
            filterRestaurants();
        });
    }
}

document.addEventListener('DOMContentLoaded', () => {
    setupSearch();
    filterRestaurants();
});