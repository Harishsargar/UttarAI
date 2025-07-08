import whatsapp from "../assets/whatsapp.png"
import "../style/usecase.css"

function Usecase(){

    return(
        <>
            <div className="usecase-component">
                <div className="usecase-main">
                    <h1 className="usecase-title">Built for Conversations That Matter</h1>
                    <h2 className="usecase-subtitle">Uttar-AI understands where you work and <br/>makes your replies faster, smarter, and easier.</h2>
                    <div className="usecase-whatsapp">
                        <div className="usecase-whatsapp-info">
                            <h2>Whatsapp Web</h2><br/>
                            <p>Uttar-AI adds this super handy "AI-Reply" button right next to the message box. When you click it, extension gets the <span className="underline">current Conversation context</span>, and automatically types out a smart reply for you. You can tweak it if you want, or just hit send</p><br/>
                            <h3 className="mini-usecase-title">How Uttar AI Helps You</h3>
                            <ul className="mini-usecase">
                                <li>Instantly generate smart replies based on chat context</li>
                                <li>Save time while multitasking no typing needed</li>
                                <li>Handle group or customer conversations more efficiently</li>
                            </ul>
                        </div>
                        <div className="usecase-whatsapp-img">
                            <img src={whatsapp} height={300} width={300}/>
                        </div>
                    </div><hr className="usecase-subdivison"/>
                    <ul className="mini-usecase">
                                <li>Instantly generate smart replies based on chat context</li>
                                <li>Save time while multitasking no typing needed</li>
                                <li>Handle group or customer conversations more efficiently</li>
                            </ul>
                </div>
            </div>
        </>
    )
}

export default Usecase;