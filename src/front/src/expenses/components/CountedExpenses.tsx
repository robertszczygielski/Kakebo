import * as React from "react";
import { useEffect, useState } from "react";
import { getCountedExpenses } from "../api/ExpensesApi";

interface ICountedExpenses {
    amount: number;
}

const CountedExpenses: React.FC = () => {
    const [expense, setExpense] = useState<ICountedExpenses | null>({amount: 0});

    useEffect(() => {
        countedExpenses();
    }, [])

    const countedExpenses = () => {
        getCountedExpenses().then((countedExpenses: number) => {
            setExpense({amount: countedExpenses});
        })
    }

    return (
        <>
            {expense?.amount}
        </>
    )
}

export default CountedExpenses;