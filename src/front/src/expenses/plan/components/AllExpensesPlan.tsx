import React, { useEffect, useState } from "react";
import { getExpensesPlan } from "../api/ExpensesPlanApi";

interface IExpensesPlan {

    id: string;
    amount: number;
    expensesCategory: string
}

export const AllExpensesPlan: React.FC = () => {
    const [expensesPlan, setExpensesPlan] = useState<Array<IExpensesPlan | null> | null>([]);

    useEffect(() => {
        findAllExpenses();
    }, [])

    const findAllExpenses = () => {
        getExpensesPlan().then((data: any) => {
            const assets: Array<IExpensesPlan | null> = data;
            setExpensesPlan(assets);
        })
    }

    return (
        <div>
            <ul>
                {expensesPlan?.map(asset => (
                    <li key={asset?.id}>{asset?.amount} - {asset?.expensesCategory}</li>
                ))}
            </ul>
        </div>
    );
}
