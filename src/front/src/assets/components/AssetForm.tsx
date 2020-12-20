import * as React from 'react';
import { Field, Form, Formik } from 'formik';
import { setAsset } from "../api/AssetApi";

interface IAsset {
    asset: number;
}

export const AssetForm: React.FC = () => {
    const initialValues: IAsset = {
        asset: 0,
    };

    const submitHandler = (values: any) => {
        setAsset(values.asset);
    }

    return (
        <div>
            <h1>Set Asset</h1>
            <Formik
                initialValues={initialValues}
                onSubmit={submitHandler}
            >
                <Form>
                    <Field type="text" id="asset" name="asset" placeholder="0"/>
                    <button type="submit">Submit</button>
                </Form>
            </Formik>
        </div>
    );
}