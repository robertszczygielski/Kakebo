import * as React from 'react';
import { Field, Form, Formik } from 'formik';
import { registerUser } from "./api/RegisterApi";

interface IFormValues {
    email: string;
    password: string;
    confirmPassword: string;
}

interface IOtherProps {
    message: string;
}

export const RegisterBasic: React.FC = () => {
    const initialValues: IFormValues = {
        email: '',
        password: '',
        confirmPassword: ''
    };

    return (
        <div>
            <h1>My Example</h1>
            <Formik
                initialValues={initialValues}
                onSubmit={(values, actions) => {
                    console.log({values, actions});
                    alert(JSON.stringify(values, null, 2));
                    actions.setSubmitting(false);
                    registerUser();
                }}
            >
                <Form>
                    <Field type="email" id="email" name="email" placeholder="email"/>
                    <Field type="password" id="password" name="password" placeholder="password"/>
                    <Field type="password" id="confirmPassword" name="confirmPassword" placeholder="confirmPassword"/>
                    <button type="submit">Submit</button>
                </Form>
            </Formik>
        </div>
    );
};
