import * as React from 'react';
import { useState } from 'react';
import { Redirect } from "react-router-dom";

export const Loguot: React.FC = () => {
    const [logged, setLogged] = useState(false);

    const handleLogout = () => {
        localStorage.removeItem('jwtToken');
        setLogged(!logged);
        window.location.reload();
    }

    return (
        <div>
            <button onClick={handleLogout}>Logout</button>
            {logged && (
                <Redirect to={'/'}/>
            )}

        </div>
    )
}