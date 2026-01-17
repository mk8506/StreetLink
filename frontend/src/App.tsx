import React from 'react'
import { Routes, Route, Navigate } from 'react-router-dom';
import Display from './components/Display.tsx'
import Search from './components/Search.tsx'
import { useState } from "react";

export default function App() {
  const [query, setQuery] = useState("");

  return (
    <>
      {/* <Routes>
        <Route path='/' element={<Navigate to={'/areas'} />} />
      </Routes> */}
      
      <Search onSearch={setQuery} />
      <Display value={query} />
    </>
  );
}
