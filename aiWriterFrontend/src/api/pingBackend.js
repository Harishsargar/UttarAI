import axios from "axios"
import { BASE_URL } from "./baseURL"


export const pingBackend = async () =>{
    const response = await axios.get(`${BASE_URL}/ping/isbackendup`);
    console.log(response);
    return response;
}


// http://localhost:8080/api/ping/isbackendup