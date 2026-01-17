export type Area = {
  zipcode: number;
  city: string;
  description: string;
  population: number;
  safety: number;
  publicEdu: number;
  affordability: number;
  traits: string[];
};

export type ApiDataAll = {
  timestamp: String;
  status: number;
  message: String;
  areas: Area[];
};

export type ApiData = {
  timestamp: String;
  status: number;
  message: String;
  areas: Area;
};

export async function getAll(input: string): Promise<ApiDataAll> {
  let url="http://localhost:8080/areas";
  const res = await fetch(`${url}`);
  if (!res.ok) throw new Error("Request failed");
  return res.json();
}

export async function getOne(input: string): Promise<ApiData> {
  let url=`http://localhost:8080/areas/${input}`;
  const res = await fetch(`${url}`);
  if (!res.ok) throw new Error("Request failed");
  return res.json();
}
