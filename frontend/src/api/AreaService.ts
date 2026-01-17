import type { ApiDataType } from '../components/Types'

export async function getAll(): Promise<ApiDataType> {
  let url="http://localhost:8080/areas";
  const res = await fetch(`${url}`);
  if (!res.ok) throw new Error("Request failed");
  return res.json();
}

export async function getOne(input: string): Promise<ApiDataType> {
  let url=`http://localhost:8080/areas/${input}`;
  const res = await fetch(`${url}`);
  if (!res.ok) throw new Error("Request failed");
  return res.json();
}