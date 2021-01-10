import * as React from 'react';
import { Field, Form, Formik } from 'formik';
import { IExpenses, setExpenses } from "../api/ExpensesApi";

export const ExpensesForm: React.FC = () => {
    const initialValues: IExpenses = {
        asset: 0,
        expensesDate: new Date(),
    };

    const submitHandler = (values: any) => {
        setExpenses(values);
    }

    return (
        <div>
            <h1>Set Expenses</h1>
            <Formik
                initialValues={initialValues}
                onSubmit={submitHandler}
            >
                <Form>
                    <Field type="text" id="expenses" name="expenses" placeholder="0"/>
                    <button type="submit">Submit</button>
                </Form>
            </Formik>
        </div>
    );
}