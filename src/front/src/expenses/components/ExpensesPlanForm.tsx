import * as React from 'react';
import { useEffect, useState } from 'react';
import { Field, Form, Formik } from 'formik';
import { getAllExpensesCategories, IExpensesPlan, setExpensesPlan } from "../api/ExpensesApi";
import CountedExpenses from "./CountedExpenses";

export const ExpensesPlanForm: React.FC = () => {
    const [expensesCategories, setExpensesCategories] = useState<Array<string>>(
        []
    );
    const initialValues: IExpensesPlan = {
        amount: 0,
        expensesCategory: "FOR_LIVE",
    };

    useEffect(() => {
        findAllExpensesPlanCategories();
    }, [])

    const findAllExpensesPlanCategories = () => {
        getAllExpensesCategories().then((data: any) => {
            const expensesCategories: Array<string> = data;
            setExpensesCategories([...expensesCategories]);
        })
    }

    const submitHandler = (values: IExpensesPlan) => {
        setExpensesPlan([values]);
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
                    <Field as="select" id="expensesCategory" name="expensesCategory">
                        {expensesCategories?.map(expensesCategory => (
                            <option value={expensesCategory}>{expensesCategory}</option>
                        ))}
                    </Field>
                    <button type="submit">Submit</button>
                </Form>
            </Formik>
        </div>
    );
}