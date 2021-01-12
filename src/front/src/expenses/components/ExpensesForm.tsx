import * as React from 'react';
import { Field, Form, Formik } from 'formik';
import { IExpenses, setExpenses } from "../api/ExpensesApi";
import CountedExpenses from "./CountedExpenses";

export const ExpensesForm: React.FC = () => {
    const initialValues: IExpenses = {
        amount: 0,
        expensesDate: new Date(),
    };

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