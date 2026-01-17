import Display from './assets/display.tsx'
import Search from './assets/search.tsx'
import { useState } from "react";

export default function App() {
  const [query, setQuery] = useState("");

  return (
    <>
      <Search onSearch={setQuery} /> 
      {/* onSearch(input) -> setQuery(input) */}
      <Display value={query}/>
    </>
  );
}
