import React, { useEffect, useState } from 'react';
import { getAllAssets } from "../api/AssetApi";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Column, useTable } from "react-table";

interface IAsset {
    id: string;
    amount: number;
    assetCategory: string;
    incomeDate: string
}


const AllAssets: React.FC = () => {
    const [assets, setAssets] = useState<Array<IAsset | null> | null>([]);

    useEffect(() => {
        findAllAssets();
    }, [])

    const data = React.useMemo<IAsset[]>(
        () => [
            {
                id: 'Hello',
                amount: 1,
                assetCategory: "a",
                incomeDate: "2014-dd"
            },
            {
                id: 'Hello',
                amount: 1,
                assetCategory: "a",
                incomeDate: "2014-dd"
            },
            {
                id: 'Hello',
                amount: 1,
                assetCategory: "a",
                incomeDate: "2014-dd"
            },
        ],
        []
    )

    const columns = React.useMemo<Column<IAsset>[]>(
        () => [
            {
                Header: 'Column 1',
                accessor: 'amount', // accessor is the "key" in the data
            },
            {
                Header: 'Column 2',
                accessor: 'assetCategory',
            },
            {
                Header: 'Column 2',
                accessor: 'incomeDate',
            },
        ],
        []
    )

    const tableInstance = useTable({columns, data})

    const findAllAssets = () => {
        getAllAssets().then((data: any) => {
            const assets: Array<IAsset | null> = data;
            setAssets(assets);
            console.log(assets);
        });
        setColumnData();
    }


    const setColumnData = () => {

    }

    return (
        <div>
            <ul>
                {assets?.map(asset => (
                    <li key={asset?.id}>
                        {asset?.amount} - {asset?.assetCategory} - {asset?.incomeDate}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default AllAssets;
