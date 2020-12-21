import ReactDOM from "react-dom";
import { AssetForm } from "./AssetForm";

test(('<AssetForm>'), () => {
    const root = document.createElement("div");
    ReactDOM.render(<AssetForm/>, root);

    // @ts-ignore
    expect(root.querySelector("h1").textContent).toBe("Set Asset");
})
