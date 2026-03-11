import React, { useState } from 'react';

const Header = ({ cities, selectedCity, setSelectedCity, searchQuery, setSearchQuery }) => {
    const [isCityOpen, setIsCityOpen] = useState(false);

    return (
        <header className="main-header">
            <a className="logo" href="/">
                <h1 className="dine">Dine</h1>
                <h1 className="out">Out</h1>
                <img src="/images/logo.png" alt="LOGO" />
            </a>

            <div className="search-wrapper">
                <div className="city-container">
                    <div className="city-selection" onClick={() => setIsCityOpen(!isCityOpen)}>
                        {selectedCity}
                    </div>
                    {isCityOpen && (
                        <div className="city-options show">
                            <div onClick={() => { setSelectedCity("Alla städer"); setIsCityOpen(false); }}>
                                Alla städer
                            </div>
                            {cities.map(city => (
                                <div key={city} onClick={() => { setSelectedCity(city); setIsCityOpen(false); }}>
                                    {city}
                                </div>
                            ))}
                        </div>
                    )}
                </div>

                <div id="search-container" className="restaurant-search-container">
                    <span className="search-icon"></span>
                    <input
                        type="text"
                        placeholder="Sök efter kategori eller restaurang..."
                        className="restaurant-search"
                        value={searchQuery}
                        onChange={(e) => setSearchQuery(e.target.value)}
                    />
                    <button id="search-button">SÖK</button>
                </div>
            </div>

            <button className="log-in-button">
                <img src="/images/log-in.png" alt="" />
                <span className="text">LOGGA IN</span>
            </button>
        </header>
    );
};

export default Header;