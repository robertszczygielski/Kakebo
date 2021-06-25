import React, { useEffect, useState } from 'react';
import { getAllAssets } from "../api/AssetApi";
import 'bootstrap/dist/css/bootstrap.min.css';
import { useTable } from "react-table";

interface IAsset {
    id: string;
    amount: number;
    assetCategory: string;
    incomeDate: string
}

const AllAssets: React.FC = () => {
    const [assets, setAssets] = useState<Array<IAsset | null> | null>([]);
    const data = React.useMemo(
        () => [
            {
                amount: 'Hello',
                assetCategory: 'World',
            },
            {
                amount: 'react-table',
                assetCategory: 'rocks',
            },
            {
                amount: 'whatever',
                assetCategory: 'you want',
            },
        ],
        [assets]
    )

    const columns = React.useMemo(
        () => [
            {
                Header: 'test',
                columns: [
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
                    }
                ],
            }
        ],
        []
    )

    const {
        getTableProps,
        getTableBodyProps,
        headerGroups,
        rows,
        prepareRow,
    } = useTable({columns, data})

    useEffect(() => {
        findAllAssets();
    }, [])

    const findAllAssets = () => {
        getAllAssets().then((data: any) => {
            const assets: Array<IAsset | null> = data;
            setAssets(assets);
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
