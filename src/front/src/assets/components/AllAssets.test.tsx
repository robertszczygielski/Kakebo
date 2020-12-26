import React from 'react';
import AllAssets from './AllAssets';
import ReactDOM from 'react-dom';

test('<AllAssets>', () => {
    const root = document.createElement("div");
    ReactDOM.render(<AllAssets/>, root);

    expect(root.querySelector("ul")).toBeDefined();
});
