import React, { useEffect, useState } from "react";
import { getAllExpenses } from "../api/ExpensesApi";

interface IExpenses {
    id: string;
    amount: number;
    expensesDate: string;
    expensesCategory: string
}

export const AllExpenses: React.FC = () => {
    const [expenses, setExpenses] = useState<Array<IExpenses | null> | null>([]);

    useEffect(() => {
        findAllExpenses();
    }, [])

    const findAllExpenses = () => {
        getAllExpenses().then((data: any) => {
            const assets: Array<IExpenses | null> = data;
            setExpenses(assets);
        })
    }

    return (
        <div>
            <ul>
                {expenses?.map(asset => (
                    <li key={asset?.id}>{asset?.amount} - {asset?.expensesCategory} - {asset?.expensesDate}</li>
                ))}
            </ul>
        </div>
    );
}
