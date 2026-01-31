import React, { useState } from 'react';
import './AreaListPage.css';
import type { Area, ApiResponse } from './Types';
import { fetchAreas } from './getData';


const AreaListPage: React.FC = () => {
  const [cityName, setCityName] = useState<string>('');
  const [zipcode, setZipcode] = useState<string>('');
  const [areas, setAreas] = useState<Area[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string>('');

  const handleSearch = (e: React.FormEvent) => {
    e.preventDefault();
    fetchAreas();
  };

  const renderStars = (rating: number) => {
    const fullStars = Math.floor(rating);
    const hasHalfStar = rating % 1 !== 0;
    const emptyStars = 5 - Math.ceil(rating);

    return (
      <div className="stars">
        {[...Array(fullStars)].map((_, i) => (
          <span key={`full-${i}`} className="star full">★</span>
        ))}
        {hasHalfStar && <span className="star half">★</span>}
        {[...Array(emptyStars)].map((_, i) => (
          <span key={`empty-${i}`} className="star empty">☆</span>
        ))}
        <span className="rating-value">{rating.toFixed(1)}</span>
      </div>
    );
  };

  return (
    <div className="area-list-page">
      <div className="container">
        <h1>Area Search</h1>

        {/* 검색 폼 */}
        <form onSubmit={handleSearch} className="search-form">
          <div className="input-group">
            <div className="input-field">
              <label htmlFor="cityName">City Name</label>
              <input
                type="text"
                id="cityName"
                value={cityName}
                onChange={(e) => setCityName(e.target.value)}
                placeholder="Enter city name"
              />
            </div>

            <div className="input-field">
              <label htmlFor="zipcode">Zipcode</label>
              <input
                type="text"
                id="zipcode"
                value={zipcode}
                onChange={(e) => setZipcode(e.target.value)}
                placeholder="Enter zipcode"
              />
            </div>
          </div>

          <button type="submit" className="search-button" disabled={loading}>
            {loading ? 'Searching...' : 'Search'}
          </button>
        </form>

        {error && (
          <div className="error-message">
            <p>⚠️ {error}</p>
          </div>
        )}

        {areas.length > 0 && (
          <div className="results">
            <h2>Found {areas.length} area{areas.length > 1 ? 's' : ''}</h2>
            
            <div className="area-list">
              {areas.map((area) => (
                <div key={area.zipcode} className="area-card">
                  <div className="area-header">
                    <h3>{area.city}</h3>
                    <span className="zipcode">Zipcode: {area.zipcode}</span>
                  </div>

                  <div className="area-stats">
                    <div className="stat">
                      <span className="stat-label">Population</span>
                      <span className="stat-value">
                        {area.population.toLocaleString()}
                      </span>
                    </div>

                    <div className="stat">
                      <span className="stat-label">Safety</span>
                      {renderStars(area.safety)}
                    </div>

                    <div className="stat">
                      <span className="stat-label">Public Education</span>
                      {renderStars(area.publicEdu)}
                    </div>

                    <div className="stat">
                      <span className="stat-label">Affordability</span>
                      {renderStars(area.affordability)}
                    </div>
                  </div>

                  <div className="area-traits">
                    {area.traits.map((trait, index) => (
                      <span key={index} className="trait-tag">
                        {trait}
                      </span>
                    ))}
                  </div>

                  <p className="area-description">{area.description}</p>
                </div>
              ))}
            </div>
          </div>
        )}

        {!loading && !error && areas.length === 0 && cityName && (
          <div className="no-results">
            <p>No areas found. Try a different search.</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default AreaListPage;
