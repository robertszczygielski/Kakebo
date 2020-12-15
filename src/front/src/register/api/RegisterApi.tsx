import axios from "axios";
import { AUTH_URI } from "../../api/main";

export const registerUser: any = () => {
    return axios.post(AUTH_URI + "createUser", {"username": "admin@admin.com", "password": "admin"})
        .then((rest) => { return rest.data })
        .catch(err => console.error(err))
}