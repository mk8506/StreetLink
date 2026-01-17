import { useState } from "react";

export default function Search(props: { onSearch: (arg0: string) => void; }) {
  const [input, setInput] = useState("");

  return (
  <div>
      <h1>Find your area</h1>
      <form>
        <input
          className={""}
          type={"text"}
          placeholder={"Search by Zip Code"}
          onChange={(e) => setInput(e.target.value)}
        />
        <button 
          type={"button"}
          onClick={() => props.onSearch(input)}>
          search
        </button>
      </form>
  </div>
  )
}