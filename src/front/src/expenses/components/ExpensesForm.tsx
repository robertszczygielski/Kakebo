import * as React from 'react';
import { useEffect, useState } from 'react';
import { Field, Form, Formik } from 'formik';
import { getAllExpensesCategories, IExpenses, setExpenses } from "../api/ExpensesApi";
import CountedExpenses from "./CountedExpenses";

export const ExpensesForm: React.FC = () => {
    const [expensesCategories, setExpensesCategories] = useState<Array<string>>(
        []
    );
    const initialValues: IExpenses = {
        amount: 0,
        expensesDate: new Date(),
    };

    useEffect(() => {
        findAllExpensesCategories();
    }, [])

    const findAllExpensesCategories = () => {
        getAllExpensesCategories().then((data: any) => {
            const expensesCategories: Array<string> = data;
            setExpensesCategories([...expensesCategories]);
        })
    }

    const submitHandler = (values: IExpenses) => {
        setExpenses([values]);
    }

    return (
        <div>
            <CountedExpenses/>
            <h1>Set Expenses</h1>
            <Formik
                initialValues={initialValues}
                onSubmit={submitHandler}
            >
                <Form>
                    <Field type="text" id="amount" name="amount" placeholder="0"/>
                    <Field as="select" id="assetCategory" name="assetCategory">
                        {expensesCategories?.map(expensesCategory =>(
                            <option value={expensesCategory}>{expensesCategory}</option>
                        ))}
                    </Field>
                    <button type="submit">Submit</button>
                </Form>
            </Formik>
        </div>
    );
}