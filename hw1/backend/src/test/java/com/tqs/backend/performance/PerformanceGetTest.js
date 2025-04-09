import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 100,
    duration: '10s',
};

export default function () {
    const vu = __VU;
    const iter = __ITER;

    const restaurantId = (vu + iter) % 3 + 1;

    const resRestaurants = http.get('http://localhost:8081/api/restaurants');
    check(resRestaurants, {
        'GET /restaurants - status 200': (r) => r.status === 200,
    });

    const resMeals = http.get(`http://localhost:8081/api/meals?restaurantId=${restaurantId}`);
    check(resMeals, {
        'GET /meals - status 200': (r) => r.status === 200,
    });

}
