import React, { useEffect, useState } from 'react';
import { getAllAssets } from "./assets/api/AssetApi";
import 'bootstrap/dist/css/bootstrap.min.css';

interface IAsset {
    id: string;
    amount: number;
}

const App: React.FC = () => {
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
                    <li key={asset?.id}>{asset?.amount}</li>
                ))}
            </ul>
        </div>
    );
}

export default App;
