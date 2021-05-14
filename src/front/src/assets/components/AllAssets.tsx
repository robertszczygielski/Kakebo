import React, { useEffect, useState } from 'react';
import { getAllAssets } from "../api/AssetApi";
import 'bootstrap/dist/css/bootstrap.min.css';
import { CopyToClipboard } from "../../copy/CopyToClipboard";
import { Button } from "react-bootstrap";

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

    const findAllAssets = () => {
        getAllAssets().then((data: any) => {
            const assets: Array<IAsset | null> = data;
            setAssets(assets);
        })
    }

    return (
        <div>
            <ul>
                {assets?.map(asset => (
                    <li key={asset?.id}>
                        {asset?.amount} - {asset?.assetCategory} - {asset?.incomeDate}
                        <CopyToClipboard text={asset?.id}>
                            <Button>Copy Id</Button>
                        </CopyToClipboard>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default AllAssets;
