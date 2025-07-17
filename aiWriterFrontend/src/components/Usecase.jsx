import whatsapp from "../assets/whatsapp-min.png"
import mail from "../assets/mail.png"
import mailext from "../assets/gmail_working.png"
import whatsappext from "../assets/whatsapp_working.png"
import "../style/usecase.css"

function Usecase(){

    return(
        <>
            <div className="usecase-component">
                <div className="usecase-main">
                    <h1 className="usecase-title">Built for Conversations That Matter</h1>
                    <h2 className="usecase-subtitle">Uttar-AI understands where you work and <br/>makes your replies faster, smarter, and easier.</h2>
                    <div className="usecase">
                        <div className="usecase-info">
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
                        <img className="whatsapp-realusage" src={whatsappext} height={200} width={500} alt="seamless integration" />
                    </div><hr className="usecase-subdivison"/>
                    <div className="usecase">
                        <div className="usecase-mail-img">
                            <img src={mail} height={350} width={350}/>
                        </div>
                        <div className="usecase-info">
                            <h2>Gmail</h2><br/>
                            <p>When you hit Reply in Gmail, Uttar AI adds an "AI Reply" button right inside the reply box. Just click it and it reads the whole email thread, <span className="underline">understands the context and tone</span>, and instantly types out a clear, personalized reply in proper <span className="underline">email format</span>. You can edit it if you want, or just hit send!</p><br/>
                            <h3 className="mini-usecase-title">How Uttar AI Helps You</h3>
                            <ul className="mini-usecase">
                                <li>Reply to long emails without stressing</li>
                                <li>Quickly write formal emails that sound professional</li>
                                <li>Save time and energy let AI do the typing for you</li>
                            </ul>
                        </div>
                        <img className="whatsapp-realusage" src={mailext} height={400} width={780} alt="seamless integration" />
                    </div><hr className="section-division"/>
                    
                </div>
            </div>
        </>
    )
}

export default Usecase;