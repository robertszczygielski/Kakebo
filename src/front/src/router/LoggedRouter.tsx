import * as React from 'react';
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { Nav } from "react-bootstrap";
import { LoginBasic } from "../login/Login";
import App from "../App";
import { AssetForm } from "../assets/components/AssetForm";
import { RegisterBasic } from "../register/Register";

export const LoggedRouter: React.FC = () => {
    return (
        <Router>
            <div>
                <Nav justify variant="tabs" defaultActiveKey="/">
                    <Nav.Item>
                        <Nav.Link href="/">Home</Nav.Link>
                    </Nav.Item>
                    <Nav.Item>
                        <Nav.Link href="/allAsset">All Assets</Nav.Link>
                    </Nav.Item>
                    <Nav.Item>
                        <Nav.Link href="/addAsset">Add Assets</Nav.Link>
                    </Nav.Item>
                    <Nav.Item>
                        <Nav.Link href="/login">Login</Nav.Link>
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
        </Router>
    )
}
