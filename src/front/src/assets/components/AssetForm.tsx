import * as React from 'react';
import { Field, Form, Formik } from 'formik';
import { getAllAssetsCategories, setAsset } from "../api/AssetApi";
import { useEffect, useState } from "react";

interface IAsset {
    asset: number;
}

interface IAssetCategory {
    id: number;
    name: string;
}

export const AssetForm: React.FC = () => {
    const [assetsCategories, setAssetsCategories] = useState<Array<string>>(
        ["SALARY", "BONUS", "LOAN_RETURNED", "RENT", "OTHER"]
    );

    const initialValues: IAsset = {
        asset: 0,
    };

    useEffect(() => {
        findAllAssetsCategories();
    }, [])


    const findAllAssetsCategories = () => {
        getAllAssetsCategories().then((data: any) => {
            const assetsCategory: Array<IAssetCategory | null> = data;
            console.log(assetsCategory);
            assetsCategory.map(it => console.log(it));
            setAssetsCategories([...assetsCategories]);
            console.log(assetsCategories);
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