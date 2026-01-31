import React, { useState } from 'react';
import type { ApiResponse, Area } from './Types';


const [cityName, setCityName] = useState<string>('');
const [zipcode, setZipcode] = useState<string>('');
const [areas, setAreas] = useState<Area[]>([]);
const [loading, setLoading] = useState<boolean>(false);
const [error, setError] = useState<string>('');

export async function fetchAreas(): Promise<void> {
  if (!cityName && !zipcode) {
    setError('Please enter at least one search criteria');
    return;
  }

  setLoading(true);
  setError('');

  try {
    // Spring Boot RequestParam 형식으로 쿼리 파라미터 구성
    const params = new URLSearchParams();
    if (cityName) params.append('city', cityName);
    if (zipcode) params.append('zipcode', zipcode);

    const response = await fetch(`http://localhost:8080/areas/search?${params.toString()}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    const data: ApiResponse = await response.json();
    setAreas(data.areas);
  } catch (err) {
    setError(err instanceof Error ? err.message : 'An error occurred');
    setAreas([]);
  } finally {
    setLoading(false);
  }
};