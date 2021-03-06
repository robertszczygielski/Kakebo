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
    background-image: linear-gradient(#3949ab 0%, #ffaaaa 100%);
    border-radius: 12px;
    border:2px solid #18ab29;
    box-shadow:inset 0px 1px 0px 0px #a4e271;
    cursor:pointer;
    display: inline-block;
    font-size: 28px;
    font-family: Baskerville;
    font-weight: bold;
    letter-spacing: .15rem;
    padding: 1px 7px 2px;
    position: relative;
    text-align: center;
    text-decoration:none;
    text-shadow: 3px 6px 1px rgba(255, 255, 255, 0.66);
    text-transform: uppercase;

`