export type Area = {
  zipcode: number;
  city: string;
  population: number;
  safety: number;
  publicEdu: number;
  affordability: number;
  traits: string[];
  description: string;
}

export type ApiResponse = {
  timestamp: string;
  status: number;
  message: string;
  areas: Area[];
}