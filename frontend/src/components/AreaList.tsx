import React from 'react'
import Area from './Area'
import type { ApiDataType } from './Types'

type AreaDataProps = {
  data: ApiDataType;
};

const AreaList = ({ data } : AreaDataProps) => {
  return (
    <main>
      {data?.status !== 200 && <div>Network failure</div>}
      <ul>
        {data?.status === 200 && data?.area?.map(area => 
          <Area key={area.zipcode} area={area} />
        )}
      </ul>
    </main>
  )
}

export default AreaList