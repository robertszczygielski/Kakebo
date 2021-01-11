import * as React from 'react';
import * as Yup from 'yup';
import { Field, Form, Formik } from 'formik';
import { registerUser } from "./api/RegisterApi";

interface IFormValues {
    email: string;
    password: string;
    confirmPassword: string;
}

export const RegisterBasic: React.FC = () => {
    const initialValues: IFormValues = {
        email: '',
        password: '',
        confirmPassword: ''
    };

    const validationSchema = Yup.object({
        email: Yup.string().email("Invalid email").required("Email is required"),
        password: Yup.string().required("Password is required"),
        confirmPassword: Yup.string().oneOf([Yup.ref("password"), ''], "Password must match").required("Password must match")
    })

    const submitHandler = (values: any) => {
        registerUser(values.email, values.password);
    }

    return (
        <div>
            <h1>Register</h1>
            <Formik
                initialValues={initialValues}
                onSubmit={submitHandler}
                validationSchema={validationSchema}
            >
                {({errors, touched}) => (<Form>
                    <Field type="email" id="email" name="email" placeholder="email"/>
                    {errors.email && touched.email ? (<div>{errors.email}</div>) : <div/>}

                    <Field type="password" id="password" name="password" placeholder="password"/>
                    {errors.password && touched.password ? (<div>{errors.password}</div>) : <div/>}

                    <Field type="password" id="confirmPassword" name="confirmPassword" placeholder="confirmPassword"/>
                    {errors.confirmPassword && touched.confirmPassword ? (<div>{errors.confirmPassword}</div>) : <div/>}

                    <button type="submit">Submit</button>
                </Form>)}
            </Formik>
        </div>
    );
};
