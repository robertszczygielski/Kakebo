import axios from "axios";
import { EXPENSES_PLAN_URI } from "../../../api/main";

export interface IExpensesPlan {
    amount: number;
    expensesCategory: string;
}

export const getExpensesPlan: any = () => {
    return axios.get(EXPENSES_PLAN_URI)
        .then((rest) => {
            return rest.data
        })
        .catch(err => console.error(err))
}

export const setExpensesPlan: any = (expenses: IExpensesPlan[]) => {
    return axios.post(EXPENSES_PLAN_URI, expenses)
        .then((rest) => {
            return rest.data
        })
        .catch(err => console.error(err))
}
