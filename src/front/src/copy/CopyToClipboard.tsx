import React, { useRef, useState } from 'react';

interface IToCopy {
    text?: string;
    children?: React.ReactNode;
}

export const CopyToClipboard: React.FC<IToCopy> = ({text, children}) => {

    const [copySuccess, setCopySuccess] = useState('');
    const textAreaRef = useRef(null);

    const copyToClipboard = (e: any) => {
        // @ts-ignore
        textAreaRef.current.select();
        document.execCommand('copy');
    };

    return (
        <div>
            {
                /* Logical shortcut for only displaying the
                   button if the copy command exists */
                document.queryCommandSupported('copy') &&
                <div>
                    <button onClick={copyToClipboard}>Copy</button>
                    {copySuccess}
                    {children}
                </div>
            }
            <form>
        <textarea
            ref={textAreaRef}
            value='Some text to copy'
        />
            </form>
        </div>
    );
}