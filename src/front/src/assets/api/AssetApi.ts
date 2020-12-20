import axios from "axios"
import { ASSETS_URI } from "../../api/main";

export const getAllAssets: any = () => {
    return axios.get(ASSETS_URI + "get")
        .then((rest) => { return rest.data })
        .catch(err => console.error(err))
}

export const setAsset: any = (value: number) => {
    return axios.post(ASSETS_URI + "set", {"amount": value})
        .then((rest) => { return rest.data })
        .catch(err => console.error(err))
}