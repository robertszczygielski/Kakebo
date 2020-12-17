import axios from "axios";
import { AUTH_URI } from "../../api/main";

export const registerUser: any = (username: string, password: string) => {
    return axios.post(AUTH_URI + "createUser", {"username": username, "password": password})
        .then((rest) => { return rest.data })
        .catch(err => console.error(err))
}