import * as React from 'react';
import { BrowserRouter as Router, Redirect, Route, Switch } from "react-router-dom";
import { Nav } from "react-bootstrap";
import { LoginBasic } from "../login/Login";
import AllAssets from "../assets/components/AllAssets";
import { AssetForm } from "../assets/components/AssetForm";
import { RegisterBasic } from "../register/Register";

export class RootRouter extends React.Component {
    render() {
        const isLogged = localStorage.jwtToken;

        if (isLogged) {
            return <Router>
                <div>
                    <Nav justify variant="tabs" defaultActiveKey="/allAsset">
                        <Nav.Item>
                            <Nav.Link href="/allAsset">All Assets</Nav.Link>
                        </Nav.Item>
                        <Nav.Item>
                            <Nav.Link href="/addAsset">Add Assets</Nav.Link>
                        </Nav.Item>
                    </Nav>

                    {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
                    <Switch>
                        <Redirect strict from="/login/ok" to="/allAsset" />
                        <Route path="/allAsset">
                            <AllAssets/>
                        </Route>
                        <Route path="/addAsset">
                            <AssetForm/>
                        </Route>
                    </Switch>
                </div>
            </Router>
        } else {
            return <Router>
                <div>
                    <Nav justify variant="tabs" defaultActiveKey="/">
                        <Nav.Item>
                            <Nav.Link href="/">Home</Nav.Link>
                        </Nav.Item>
                        <Nav.Item>
                            <Nav.Link href="/login">Login</Nav.Link>
                        </Nav.Item>
                    </Nav>
                    <Switch>
                        <Redirect strict from="/login/ok" to="/allAsset" />
                        <Route path="/login">
                            <LoginBasic/>
                        </Route>
                        <Route path="/">
                            <RegisterBasic/>
                        </Route>
                    </Switch>
                </div>
            </Router>
        }

    }
}