import * as React from 'react';
import { useEffect } from 'react';
import { Field, Form, Formik } from 'formik';
import { getAllExpensesCategories, IExpenses, setExpenses } from "../api/ExpensesApi";
import CountedExpenses from "./CountedExpenses";

export const ExpensesForm: React.FC = () => {
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
            console.log(expensesCategories);
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
                    <button type="submit">Submit</button>
                </Form>
            </Formik>
        </div>
    );
}