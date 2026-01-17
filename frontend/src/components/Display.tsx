import React, { useEffect, useState } from 'react'
import AreaList from './AreaList'
import { getOne } from '../api/AreaService'
import type { ApiDataType } from './Types';

const Display = ({ value }: { value: string }) => {
  const [data, setData] = useState<ApiDataType>(
    {
        timestamp: "0",
        status: 0,
        message: "Initial",
        area: []
    }
  );

  useEffect(() => {
    async function load() {
      try {
        const result = await getOne(value); // Promise resolves here
        setData(result);               // now data is real, not Promise
      } catch (err) {
        console.error(err);
      }
    }

    load();
  }, [value]);  

  return (
    <div>
      {data?.area?.length === 0 && <div>No area found</div>}
      {data?.area?.length !== 0 &&<AreaList data={data}/>}
    </div>
  )
}

export default Display