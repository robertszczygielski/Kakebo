import axios from "axios"
import { EXPENSES_URI } from "../../api/main";


export interface IExpenses {
    amount: number;
    expensesDate: Date;
}

export const getCountedExpenses: any = () => {
    return axios.get(EXPENSES_URI + "counted")
        .then((rest) => {
            return rest.data
        })
        .catch(err => console.error(err))
}

export const setExpenses: any = (expenses: IExpenses[]) => {
    return axios.post(EXPENSES_URI, expenses)
        .then((rest) => {
            return rest.data
        })
        .catch(err => console.error(err))
}