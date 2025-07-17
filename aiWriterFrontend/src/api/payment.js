import axios from 'axios'
import { BASE_URL } from './baseURL';

// const base_url = 'http://localhost:8080/api/secure';
// const base_url = 'https://uttarai-kdfm.onrender.com/api/secure';

// create order
export const createOrder = async (body) => {
    const token = localStorage.getItem('token');
    const headers = {
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    }
    const response = await axios.post(`${BASE_URL}/payment/createorder`, body, headers);
    console.log(response);
    return response;
}


//verify payment at backend
export const verifyPayment = async (body) => {
    const token = localStorage.getItem('token');
    const headers = {
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    }
    const response = await axios.post(`${BASE_URL}/payment/verifypayment`, body, headers);
    console.log(response);
    return response;
}


//fetch current plan
export const fetchCurrentPlan = async () => {
    const token = localStorage.getItem('token');
    const headers = {
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    }
    const response = await axios.get(`${base_url}/payment/currentplan`, headers);
    console.log(response);
    return response;
}