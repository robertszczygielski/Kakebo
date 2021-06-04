import * as React from 'react';
import { useState } from 'react';
import { Redirect } from "react-router-dom";
import styled from "styled-components";

export const Loguot: React.FC = () => {
    const [logged, setLogged] = useState(false);

    const handleLogout = () => {
        localStorage.removeItem('jwtToken');
        setLogged(!logged);
        window.location.reload();
    }

    return (
        <div>
            <StyledButton onClick={handleLogout}>Logout</StyledButton>
            {logged && (
                <Redirect to={'/'}/>
            )}
        </div>
    )
}

const StyledButton = styled.button`
    position: relative;
    background-color: #3949ab;
    font-size: 28px;
    padding: 1px 7px 2px;
    border-radius: 12px;
    alignItems: 'center';
`