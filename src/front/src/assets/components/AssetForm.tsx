import * as React from 'react';
import { useEffect, useState } from 'react';
import { Field, Form, Formik } from 'formik';
import { getAllAssetsCategories, setAsset } from "../api/AssetApi";

interface IAsset {
    asset: number;
    assetCategory: string;
}

export const AssetForm: React.FC = () => {
    const [assetsCategories, setAssetsCategories] = useState<Array<string>>(
        []
    );

    const initialValues: IAsset = {
        asset: 0,
        assetCategory: "",
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

    const submitHandler = (values: any, {resetForm}: any) => {
        setAsset(values.asset, values.assetCategory).then(() => {
            resetForm({initialValues});
        });
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
                    <Field as="select" id="assetCategory" name="assetCategory">
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