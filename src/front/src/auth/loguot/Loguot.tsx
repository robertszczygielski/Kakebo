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
    alignItems: 'center';
    background-color: #3949ab;
    border-radius: 12px;
    box-shadow:inset 0px 1px 0px 0px #a4e271;
    font-size: 28px;
    font-family: Baskerville;
    font-weight: bold;
    padding: 1px 7px 2px;
    position: relative;
    text-decoration:none;
`