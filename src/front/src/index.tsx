import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import reportWebVitals from './reportWebVitals';
import setAuthenticationToken from "./api/main";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { LoginBasic } from "./login/Login";
import { AssetForm } from "./assets/components/AssetForm";
import { RegisterBasic } from "./register/Register";
import App from "./App";
import { Nav } from "react-bootstrap";

setAuthenticationToken(localStorage.jwtToken);

ReactDOM.render(
    <Router>
        <div>
            <Nav variant="tabs" defaultActiveKey="/">
                <Nav.Item>
                    <Nav.Link href="/">Home</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="/login">Login</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="/allAsset">All Assets</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="/addAsset">Add Assets</Nav.Link>
                </Nav.Item>
            </Nav>

            {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
            <Switch>
                <Route path="/login">
                    <LoginBasic/>
                </Route>
                <Route path="/allAsset">
                    <App/>
                </Route>
                <Route path="/addAsset">
                    <AssetForm/>
                </Route>
                <Route path="/">
                    <RegisterBasic/>
                </Route>
            </Switch>
        </div>
    </Router>,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
