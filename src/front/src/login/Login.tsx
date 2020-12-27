import * as React from 'react';
import { Field, Form, Formik } from 'formik';
import { loginUser } from './api/LoginApi';
import setAuthenticationToken from "../api/main";
import styled from 'styled-components';

interface IFormValues {
    email: string;
    password: string;
}

const StyledButton = styled.button`
  font-size: 1em;
  margin: 1em;
  padding: 0.25em 1em;
  border-radius: 3px;

  color: mediumseagreen;
  border: 2px solid mediumseagreen;
`;

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
                    <br/>
                    <Field type="password" id="password" name="password" placeholder="password"/>
                    <br/>
                    <StyledButton type="submit">Submit</StyledButton>
                </Form>
            </Formik>
        </div>
    );
}