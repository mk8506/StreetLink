import { useEffect, useState } from "react";
import type { ApiDataAll, ApiData } from "./getData";
import { getAll, getOne } from "./getData";

export default function Display(props: { value: string; }) {

  const [dataList, setDataList] = useState<ApiDataAll | null>(null);
  const [data, setData] = useState<ApiData | null>(null);
  const [cityName, setCityName] = useState("");

  useEffect(() => {
    if (props.value === "") {
      getAll(props.value).then(setDataList).catch(console.error);
    } else {
      getOne(props.value).then(setData).catch(console.error);
    }
  }, [props.value]);

  useEffect(() => {
    if (props.value === "") {
      setCityName(dataList?.areas[0].city!); //trust it's not null
    } else {
      setCityName(data?.areas.city!);
    }
  }, [props.value]);

  return (
      <div>
        <p>
          city: {cityName}
        </p>
        <pre>{JSON.stringify(dataList, null, 2)}</pre>
      </div>
  );
}
