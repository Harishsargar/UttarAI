import "../style/instructions.css";

function Instructions() {
    return (
        <>
            <div className="instruction-component">
                <div className="instruction-main">
                    <h1>Get the Extension in 3 Easy steps</h1>
                    <div className="download inst ">
                        <div className="instruction-step">1</div>
                        <div className="inst-desc">
                            <div className="inst-button"><a className="inst-extension" href="/uttar-ai-extension.zip"
      download><div>Get Extension</div></a>
                            </div>
                            <p> Download the Uttar-AI Extension and <span>unzip</span> it </p>
                            <p>( currently unavailable on chrome store as it is paid )</p>
                        </div>
                    </div>
                    <div className="mangage-extension inst">
                        <div className="instruction-step">2</div>
                        <div className="inst-desc">
                            <p>Go to <span>chrome://extensions/</span> in chrome broswer</p>
                            <p>Toggle the <span>Developer Mode</span> in top right corner</p>
                        </div>
                    </div>

                    <div className="load-unpacked inst">
                        <div className="instruction-step">3</div>
                        <div className="inst-desc">
                            <p>Click on the <span>Load Unpacked</span> button</p>
                            <p> Choose the extension folder downloaded in 1 step</p>
                        </div>
                    </div>
                    <h1> hooray!! that's it.</h1>
                </div>
            </div ><hr className="section-division"/>
        </>
    )
}

export default Instructions;
