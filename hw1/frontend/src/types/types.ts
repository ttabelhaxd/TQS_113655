export interface Restaurant {
    id: number;
    name: string;
    location: string;
  }

export interface Reservation {
    token: string
    restaurantName: string
    date: string
    checkedIn: boolean
    cancelled: boolean
    type: "ALMOCO" | "JANTAR"
  }
  
export interface WeatherForecast {
    date: string;
    maxTemp: number;
    minTemp: number;
    precipitation: number;
  }
  
export interface Meal {
    date: string;
    description: string;
    type: "ALMOCO" | "JANTAR";
    forecast: WeatherForecast;
  }
  
export interface GroupedMeal {
    date: string;
    almoco?: Meal;
    jantar?: Meal;
    forecast?: WeatherForecast;
  }