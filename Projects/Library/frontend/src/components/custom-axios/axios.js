import axios from "axios";

const instance = axios.create({
    baseURL : 'localhost:8080/api'
    headers : {
    'Access-Control-Allow-Origin' : '*'}
})

export default axios;