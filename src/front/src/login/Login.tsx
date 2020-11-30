import React from 'react';
import { withFormik, FormikProps, FormikErrors, Form, Field } from 'formik';

interface IFormValues {
    email: string;
    password: string;
}

interface IOtherProps {
    message: string;
}

const InnerForm = (props: IOtherProps & FormikProps<IFormValues>) => {
    const { touched, errors, isSubmitting, message } = props;
    return (
        <Form>
            <h1>{message}</h1>

            <Field type="email" name="email" />
            {touched.email && errors.email && <div>{errors.email}</div>}

            <Field type="password" name="password" />
            {touched.password && errors.password && <div>{errors.password}</div>}

            <button type="submit" disabled={isSubmitting}>
                Submit
            </button>
        </Form>
    );
};

interface IFormLoginProps {
    initialEmail?: string;
    message: string;
}

const LoginForm = withFormik<IFormLoginProps, IFormValues>({
    mapPropsToValues: props => {
        return {
            email: props.initialEmail || '',
            password: '',
        };
    },

    validate: (values: IFormValues) => {
        let errors: FormikErrors<IFormValues> = {};

        function isValidEmail(email: string) {
            return false;
        }

        if (!values.email) {
            errors.email = 'Required';
        } else if (!isValidEmail(values.email)) {
            errors.email = 'Invalid email address';
        }
        return errors;
    },

    handleSubmit: values => {

    },
})(InnerForm);

const LoginBasic = () => (
    <div>
        <LoginForm message="Sign up" />
    </div>
);


export default LoginBasic;