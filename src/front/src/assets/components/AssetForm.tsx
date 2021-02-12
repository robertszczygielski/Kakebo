import * as React from 'react';
import { Field, Form, Formik } from 'formik';
import { getAllAssetsCategories, setAsset } from "../api/AssetApi";
import { useEffect, useState } from "react";

interface IAsset {
    asset: number;
}

export const AssetForm: React.FC = () => {
    const [assetsCategories, setAssetsCategories] = useState<Array<string>>(
        []
    );

    const initialValues: IAsset = {
        asset: 0,
    };

    useEffect(() => {
        findAllAssetsCategories();
    }, [])


    const findAllAssetsCategories = () => {
        getAllAssetsCategories().then((data: any) => {
            const assetsCategory: Array<string> = data;
            setAssetsCategories([...assetsCategory]);
        })
    }

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
                    <Field as="select" id="assetCategory">
                        {assetsCategories?.map(assetsCategory =>(
                            <option value={assetsCategory}>{assetsCategory}</option>
                        ))}
                    </Field>
                    <button type="submit">Submit</button>
                </Form>
            </Formik>
        </div>
    );
}