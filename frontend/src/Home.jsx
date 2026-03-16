import React, {useState, useEffect, useRef, useMemo} from 'react';
import Header from './Header';

const Main = () => {
    const [restaurants, setRestaurants] = useState([]);
    const [categories, setCategories] = useState([]);
    const [cities, setCities] = useState([]);
    const categoryContainerRef = useRef(null);

    const [selectedCity, setSelectedCity] = useState("Alla städer");
    const [selectedCategory, setSelectedCategory] = useState("Alla kategorier");
    const [searchQuery, setSearchQuery] = useState("");
    const [searchResult, setSearchResult] = useState("");

    const [canScroll, setCanScroll] = useState({left : false, right :false});
    const [isAtStart, setIsAtStart] = useState(true);
    const [isAtEnd, setIsAtEnd] = useState(false);


    useEffect(() => {
        fetch('/api/init-data')
            .then(res => res.json())
            .then(data => {
                setRestaurants(data.restaurants);
                setCategories(data.categories);
                setCities(data.cities);
            })
            .catch(err => console.error("Could not fetch data:", err));
    }, []);

    useEffect(() => {
        checkScroll();
    }, [categories]);

    const handleSearch = (e) => {
        if (e) e.preventDefault();
        setSearchResult(searchQuery);
    }

    const clearSearch = () => {
        setSearchQuery("");
        setSearchResult("");
    };

    const handleCategoryClick = (category) => {
        setSelectedCategory(category);
        clearSearch();
    }

    const checkScroll = () => {
        const container = categoryContainerRef.current;
        if(container) {
            const { scrollLeft, scrollWidth, clientWidth } = container;
            setIsAtStart(scrollLeft<=0);
            setIsAtEnd(scrollLeft + clientWidth >= scrollWidth);
            setCanScroll({
                left: scrollLeft > 0,
                right: scrollLeft + clientWidth < scrollWidth
            })
        }
    }

    const scroll = (offset) => {
        categoryContainerRef.current?.scrollBy({ left: offset, behavior: 'smooth' });
    };

    const filteredRestaurants = useMemo(() => {
        return restaurants.filter(res => {
            const matchesSearch = res.name.toLowerCase().includes(searchResult.toLowerCase());
            const cityMatch = selectedCity === "Alla städer" || res.address.city === selectedCity;
            const categoryMatch = selectedCategory === "Alla kategorier" || res.category === selectedCategory;

            //If user searches after a restaurant ignore category
            if (searchResult !== "") {
                return matchesSearch && cityMatch;
            }

            return categoryMatch && cityMatch;
        });
    }, [restaurants, searchResult, selectedCity, selectedCategory]);

    return (
        <div className="app-container">
            <Header
                cities={cities}
                selectedCity={selectedCity}
                setSelectedCity={setSelectedCity}
                searchQuery={searchQuery}
                setSearchQuery={setSearchQuery}
                onSearch={handleSearch}
            />

            <div className={`category-section-wrapper page-width ${isAtStart ? 'at-start' : ''} ${isAtEnd ? 'at-end' : ''}`}>
                <div className="category-container" ref={categoryContainerRef} onScroll={checkScroll}>
                    <button className={`category-scroll left ${canScroll.left ? 'visible' : ''}`} onClick={() => scroll(-700)}>
                        <span className="arrow left"></span>
                    </button>
                    <div className="category-cards" onClick={() => {handleCategoryClick("Alla kategorier");}}>
                        <img src="/images/categories/allaKategorier.png" alt="ALLA" />
                        <span>Alla kategorier</span>
                    </div>
                    {categories?.map(cat => (
                        <div key={cat} className="category-cards" onClick={() => {handleCategoryClick(cat);}}>
                            <img src={`/images/categories/${cat.toLowerCase()}.png`} alt={cat} />
                            <span>{cat}</span>
                        </div>
                    ))}
                    <button className={`category-scroll right ${canScroll.right ? 'visible' : ''}`} onClick={() => scroll(700)}>
                        <span className="arrow right"></span>
                    </button>
                </div>
            </div>

            <div className="restaurant-grid page-width">
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
