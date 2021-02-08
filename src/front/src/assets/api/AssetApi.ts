import axios from "axios"
import { ASSETS_URI } from "../../api/main";

export const getAllAssets: any = () => {
    return axios.get(ASSETS_URI)
        .then((rest) => { return rest.data })
        .catch(err => console.error(err))
}

export const getAllAssetsCategories: any = () => {
    return axios.get(ASSETS_URI + "categories")
        .then((rest) => { return rest.data })
        .catch(err => console.error(err))
}

export const setAsset: any = (value: number) => {
    return axios.post(ASSETS_URI, {"amount": value})
        .then((rest) => { return rest.data })
        .catch(err => console.error(err))
}