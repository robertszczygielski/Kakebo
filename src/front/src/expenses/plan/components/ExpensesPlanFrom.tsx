import * as React from "react";
import { useEffect, useState } from "react";
import { getAllExpensesCategories, setExpenses } from "../../api/ExpensesApi";
import { Field, Form, Formik } from "formik";
import { IExpensesPlan } from "../api/ExpensesPlanApi";

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

    const submitHandler = (values: IExpensesPlan, {resetForm}: any) => {
        setExpenses([values]).then(() => {
            resetForm({initialValues})
        });
    }

    return (
        <div>
            <h1>Set Expenses Plan</h1>
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