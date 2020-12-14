import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.headers.get['Accept'] = 'application/json'
axios.defaults.headers.post['Accept'] = 'application/json'

export const ASSETS_URI: string = 'http://localhost:8080/asset/'
export const AUTH_URI: string = 'http://localhost:8080/auth/'