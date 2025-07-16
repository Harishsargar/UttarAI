import axios from 'axios';

// const base_url ="http://localhost:8080/api";
const base_url ="https://uttarai-kdfm.onrender.com/api"; 


//regiester user api call.
export const registerUser = async (userData) =>{
    const response = await axios.post(`${base_url}/auth/register`, userData);
    return response;   
}

//login user api call.
export const loginUser = async (credentials) =>{
    const response = await axios.post(`${base_url}/auth/login`, credentials);
    console.log(response);
    return response;
};




