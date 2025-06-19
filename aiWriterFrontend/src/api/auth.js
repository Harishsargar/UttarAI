import axios from 'axios';

const base_url ="";


//regiester user api call.
export const registerUser = async (userData) =>{
    const responce = await axios.post(`${base_url}/register`, userData);
    return responce.data;   
}

//login user api call.
export const loginUser = async (credentials) =>{
    const responce = await axios.post(`${base_url}/login`, credentials);
    return responce.data;
};




