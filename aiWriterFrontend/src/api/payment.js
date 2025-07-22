import axios from 'axios'
import { BASE_URL } from './baseURL';

// const base_url = 'http://localhost:8080/api/secure';
// const base_url = 'https://uttarai-kdfm.onrender.com/api/secure';



function isTokenExpired(token) {
  if (!token) return true;

  try {
    const payload = JSON.parse(atob(token.split('.')[1]));
    const currentTime = Math.floor(Date.now() / 1000); // current time in seconds
    return payload.exp < currentTime;
  } catch (e) {
    console.error('Invalid token format:', e);
    return true; // Treat invalid token as expired
  }
}

function removeTokenFromStorage() {
  return Promise.resolve(localStorage.removeItem('token'));
  
}


// create order
export const createOrder = async (body) => {
    const token = localStorage.getItem('token');
    if (isTokenExpired(token)) {
        await removeTokenFromStorage();
        alert("token is expire, Login in again")
        return;
      }
    const headers = {
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    }
    const response = await axios.post(`${BASE_URL}/secure/payment/createorder`, body, headers);
    console.log(response);
    return response;
}


//verify payment at backend
export const verifyPayment = async (body) => {
    const token = localStorage.getItem('token');
    if (isTokenExpired(token)) {
        await removeTokenFromStorage();
        alert("token is expire, Login in again")
        return;
      }
    const headers = {
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    }
    const response = await axios.post(`${BASE_URL}/secure/payment/verifypayment`, body, headers);
    console.log(response);
    return response;
}


//fetch current plan
export const fetchCurrentPlan = async () => {
    const token = localStorage.getItem('token');
    if (isTokenExpired(token)) {
        await removeTokenFromStorage();
        // alert("token is expire, Login in again")
        return;
      }
    const headers = {
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    }
    const response = await axios.get(`${BASE_URL}/secure/payment/currentplan`, headers);
    console.log(response);
    return response;
}