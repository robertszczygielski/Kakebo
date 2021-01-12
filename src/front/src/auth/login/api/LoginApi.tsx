import axios from "axios";
import { AUTH_URI } from "../../../api/main";

export const loginUser: any = (username: string, password: string) => {
    return axios.post(AUTH_URI, {"username": username, "password": password})
        .then((rest) => { return rest.data })
        .catch(err => console.error(err))
}