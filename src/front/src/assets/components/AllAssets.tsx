import React, { useEffect, useState } from 'react';
import { getAllAssets } from "../api/AssetApi";
import 'bootstrap/dist/css/bootstrap.min.css';

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

    const data = React.useMemo(
        () => [
            {
                col1: 'Hello',
                col2: 'World',
            },
            {
                col1: 'react-table',
                col2: 'rocks',
            },
            {
                col1: 'whatever',
                col2: 'you want',
            },
        ],
        []
    )
    
    const columns = React.useMemo(
        () => [
            {
                Header: 'Column 1',
                accessor: 'col1', // accessor is the "key" in the data
            },
            {
                Header: 'Column 2',
                accessor: 'col2',
            },
        ],
        []
    )

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
