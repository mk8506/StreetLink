export type AreaType = {
  zipcode: number;
  city: string;
  description: string;
  population: number;
  safety: number;
  publicEdu: number;
  affordability: number;
  traits: string[];
};

export type ApiDataType = {
  timestamp: String;
  status: number;
  message: String;
  area: AreaType[];
};

