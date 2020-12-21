import React from 'react';
import App from './App';
import ReactDOM from 'react-dom';

test('<App>', () => {
    const root = document.createElement("div");
    ReactDOM.render(<App/>, root);

    expect(root.querySelector("ul")).toBeDefined();
});
