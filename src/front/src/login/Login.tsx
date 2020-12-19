import * as React from 'react';
import { Field, Form, Formik } from 'formik';
import { loginUser } from './api/LoginApi';
import setAuthenticationToken from "../api/main";

interface IFormValues {
    email: string;
    password: string;
}

export const LoginBasic: React.FC = () => {
    const initialValues: IFormValues = {
        email: '',
        password: '',
    };

    const submitHandler = (values: any) => {
        loginUser(values.email, values.password)
            .then((data: any) => {
                const token = data.jwtToken;
                localStorage.setItem('jwtToken', token);
                setAuthenticationToken(token);
            })
    }

    return (
        <div>
            <h1>My Example</h1>
            <Formik
                initialValues={initialValues}
                onSubmit={submitHandler}
            >
                <Form>
                    <Field type="email" id="email" name="email" placeholder="email"/>
                    <Field type="password" id="password" name="password" placeholder="password"/>
                    <button type="submit">Submit</button>
                </Form>
            </Formik>
        </div>
    );
}