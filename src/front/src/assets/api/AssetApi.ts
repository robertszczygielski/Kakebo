import axios from "axios"
import { ASSETS_URI } from "../../api/main";

export const getAllAssets: any = () => {
    return axios.get(ASSETS_URI + "get")
        .then((rest) => { return rest.data })
        .catch(err => console.error(err))
}