import axios from 'axios'

const base_url = 'http://localhost:8080/api/secure';
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
    const response = await axios.post(`${base_url}/payment/createorder`, body, headers);
    console.log(response);
    return response;
}