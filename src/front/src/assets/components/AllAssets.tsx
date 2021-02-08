import React, { useEffect, useState } from 'react';
import { getAllAssets, getAllAssetsCategories } from "../api/AssetApi";
import 'bootstrap/dist/css/bootstrap.min.css';

interface IAsset {
    id: string;
    amount: number;
}

interface IAssetCategory {
    id: string;
    category: string;
}

const AllAssets: React.FC = () => {
    const [assets, setAssets] = useState<Array<IAsset | null> | null>([]);
    const [assetsCategories, setAssetsCategories] = useState<Array<IAssetCategory | null> | null>([]);

    useEffect(() => {
        findAllAssets();
        findAllAssetsCategories();
    }, [])

    const findAllAssets = () => {
        getAllAssets().then((data: any) => {
            const assets: Array<IAsset | null> = data;
            setAssets(assets);
        })
    }

    const findAllAssetsCategories = () => {
        getAllAssetsCategories().then((data: any) => {
            const assetsCategory: Array<IAssetCategory | null> = data;
            console.log(assetsCategory);
            setAssetsCategories(assetsCategory);
        })
    }

    return (
        <div>
            <ul>
                {assets?.map(asset => (
                    <li key={asset?.id}>{asset?.amount}</li>
                ))}
            </ul>
        </div>
    );
}

export default AllAssets;
