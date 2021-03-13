import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.headers.get['Accept'] = 'application/json'
axios.defaults.headers.post['Accept'] = 'application/json'

export const ASSETS_URI: string = 'http://localhost:8080/asset/'
export const EXPENSES_URI: string = 'http://localhost:8080/expenses/'
export const EXPENSES_PLAN_URI: string = 'http://localhost:8080/expenses/plan'
export const AUTH_URI: string = 'http://localhost:8080/auth/'

export default function setAuthenticationToken(token: string) {
    if (token) {
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
    } else {
        delete axios.defaults.headers.common['Authorization'];
    }
}