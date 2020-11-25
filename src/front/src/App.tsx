import React, {useEffect} from 'react';
import {getAllAssets} from "./assets/api/AssetApi";

interface IAsset {
    id: string;
    amount: number;
}

const App: React.FC = () => {

    useEffect(() => {
        findAllAssets();
    }, [])

    const findAllAssets = () => {
        getAllAssets().then((data: any) => {
                console.dir(data)
            })
    }

    return (
        <div>
            <p>Hello</p>
        </div>
    );
}

export default App;
