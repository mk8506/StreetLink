import React from 'react'
import { Routes, Route, Navigate } from 'react-router-dom';
import Display from './components/Display.tsx'
import Search from './components/Search.tsx'
import { useState } from "react";
import type { Inputs } from './components/Types.tsx';
import AreaListPage from './components/AreaListPage.tsx';

export default function App() {
  const [query, setQuery] = useState("");

  return (
    <>
      {/* <Routes>
        <Route path='/' element={<Navigate to={'/areas'} />} />
      </Routes> */}
      
      {/* <Search onSearch={(inputs: Inputs) => {setQuery(inputs.city);} }  />
      <Display value={query} /> */}
      <AreaListPage></AreaListPage>
    </>
  );
}
