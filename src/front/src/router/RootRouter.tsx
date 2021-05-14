import * as React from 'react';
import { BrowserRouter as Router, Redirect, Route, Switch } from "react-router-dom";
import { Nav } from "react-bootstrap";
import { LoginBasic } from "../auth/login/Login";
import AllAssets from "../assets/components/AllAssets";
import { AssetForm } from "../assets/components/AssetForm";
import { RegisterBasic } from "../auth/register/Register";
import { Loguot } from "../auth/loguot/Loguot";
import { ExpensesForm } from "../expenses/components/ExpensesForm";
import { ExpensesPlanForm } from "../expenses/components/ExpensesPlanForm";
import { AllExpenses } from "../expenses/components/AllExpenses";
import { AllExpensesPlan } from "../expenses/plan/components/AllExpensesPlan";

export class RootRouter extends React.Component {
    render() {
        const isLogged = localStorage.jwtToken;

        if (isLogged) {
            return <Router>
                <div>
                    <Nav justify variant="tabs" defaultActiveKey="/allAsset">
                        <Nav.Item>
                            <Nav.Link href="/asset/all">All Assets</Nav.Link>
                        </Nav.Item>
                        <Nav.Item>
                            <Nav.Link href="/asset/add">Add Assets</Nav.Link>
                        </Nav.Item>
                        <Nav.Item>
                            <Nav.Link href="/expenses/all">All Expenses</Nav.Link>
                        </Nav.Item>
                        <Nav.Item>
                            <Nav.Link href="/expenses/add">Add Expenses</Nav.Link>
                        </Nav.Item>
                        <Nav.Item>
                            <Nav.Link href="/plan/expenses/add">Add Expenses Plan</Nav.Link>
                        </Nav.Item>
                        <Nav.Item>
                            <Nav.Link href="/plan/expenses/all">All Expenses Plan</Nav.Link>
                        </Nav.Item>
                        <Nav.Item>
                            <Nav.Link href="/logout">Logout user</Nav.Link>
                        </Nav.Item>
                    </Nav>

                    {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
                    <Switch>
                        <Redirect strict from="/login/ok" to="/asset/all"/>
                        <Route path="/asset/all">
                            <AllAssets/>
                        </Route>
                        <Route path="/asset/add">
                            <AssetForm/>
                        </Route>
                        <Route path="/expenses/all">
                            <AllExpenses/>
                        </Route>
                        <Route path="/expenses/add">
                            <ExpensesForm/>
                        </Route>
                        <Route path="/plan/expenses/add">
                            <ExpensesPlanForm/>
                        </Route>
                        <Route path="/plan/expenses/all">
                            <AllExpensesPlan/>
                        </Route>
                        <Route path="/logout">
                            <Loguot/>
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
                        <Redirect strict from="/login/ok" to="/allAsset"/>
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