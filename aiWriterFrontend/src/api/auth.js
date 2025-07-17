import axios from 'axios';
import { BASE_URL } from './baseURL';


// const base_url ="http://localhost:8080/api";
// const base_url ="https://uttarai-kdfm.onrender.com/api"; 


//regiester user api call.
export const registerUser = async (userData) =>{
    const response = await axios.post(`${BASE_URL}/auth/register`, userData);
    return response;   
}

//login user api call.
export const loginUser = async (credentials) =>{
    const response = await axios.post(`${BASE_URL}/auth/login`, credentials);
    console.log(response);
    return response;
};




