import axios from "axios"
import { ASSETS_URI } from "../../api/main";

const EXPENSES_URI = ASSETS_URI + "/expenses";


export interface IExpenses {
    asset: number;
    expensesDate: Date;
}

export const getCountedExpenses: any = () => {
    return axios.get(EXPENSES_URI + "/counted")
        .then((rest) => { return rest.data })
        .catch(err => console.error(err))
}

export const setExpenses: any = (value: IExpenses) => {
    return axios.post(EXPENSES_URI, {value})
        .then((rest) => { return rest.data })
        .catch(err => console.error(err))
}