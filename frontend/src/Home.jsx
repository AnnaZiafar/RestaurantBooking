import React, { useState, useEffect, useRef } from 'react';
import Header from './Header';

const Main = () => {
    const [restaurants, setRestaurants] = useState([]);
    const [categories, setCategories] = useState([]);
    const [cities, setCities] = useState([]);
    const [selectedCity, setSelectedCity] = useState("Alla städer");
    const [selectedCategory, setSelectedCategory] = useState("Alla kategorier");
    const [searchQuery, setSearchQuery] = useState("");

    const categoryContainerRef = useRef(null);

    useEffect(() => {
        fetch('/api/init-data')
            .then(res => res.json())
            .then(data => {
                setRestaurants(data.restaurants);
                setCategories(data.categories);
                setCities(data.cities);
            })
            .catch(err => console.error("Kunde inte hämta data:", err));
    }, []);

    // Den kombinerade filterlogiken
    const filteredRestaurants = restaurants.filter(res => {
        const matchesSearch = res.name.toLowerCase().includes(searchQuery.toLowerCase());
        const cityMatch = selectedCity === "Alla städer" || res.address.city === selectedCity;
        const categoryMatch = selectedCategory === "Alla kategorier" || res.category === selectedCategory;

        if (searchQuery.length > 0) {
            return matchesSearch && cityMatch;
        }
        return categoryMatch && cityMatch;
    });

    const scroll = (offset) => {
        categoryContainerRef.current?.scrollBy({ left: offset, behavior: 'smooth' });
    };

    return (
        <div className="app-container">
            <Header
                cities={cities}
                selectedCity={selectedCity}
                setSelectedCity={setSelectedCity}
                searchQuery={searchQuery}
                setSearchQuery={setSearchQuery}
            />

            <div className="category-section-wrapper">
                <button className="category-scroll" onClick={() => scroll(-1100)}>
                    <span className="arrow left"></span>
                </button>
                <div className="category-container" ref={categoryContainerRef}>
                    <div className="category-cards" onClick={() => {setSelectedCategory("Alla kategorier"); setSearchQuery("");}}>
                        <img src="/images/categories/allaKategorier.png" alt="ALLA" />
                        <span>Alla kategorier</span>
                    </div>
                    {categories?.map(cat => (
                        <div key={cat} className="category-cards" onClick={() => {setSelectedCategory(cat); setSearchQuery("");}}>
                            <img src={`/images/categories/${cat.toLowerCase()}.png`} alt={cat} />
                            <span>{cat}</span>
                        </div>
                    ))}
                </div>
                <button className="category-scroll right" onClick={() => scroll(1100)}>
                    <span className="arrow right"></span>
                </button>
            </div>

            <div className="restaurant-grid">
                {(filteredRestaurants || []).map(res => (
                    <div key={res.id} className="restaurant-cards">
                        <img src={res.imagePath} loading="lazy" alt={res.name} />
                        <div className="restaurant-card-info">
                            <span>{res.name}</span>
                            <span className="restaurant-card-rating">{res.rating}</span>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Main;
