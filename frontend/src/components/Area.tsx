import React from 'react'
import type { AreaType } from './Types'

type AreaProps = {
  area: AreaType;
};

const Area = ({ area } : AreaProps) => {
  return (
    <a href={`/areas/${area.zipcode}`}>
      <div>
        {area.city} {area.zipcode}
      </div>
    </a>
  )
}

export default Area