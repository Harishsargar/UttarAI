
import "../style/hero.css";
import robo from "../assets/robo.png"
import mail from "../assets/mail.png"
import whatsapp from "../assets/whatsapp.png"
import chat from "../assets/chat.png"
import puzzle from "../assets/puzzle.png"

function Hero() {

    return (
        <>
            <div className="hero-component">
                <div className="hero-main">
                    <h3>Welcome to</h3>
                    <h1>उत्तर-AI</h1>
                    <p className="hero-heading" ><span>Uttar-AI</span> is Context-aware, intelligent Reply-Generation platform.</p>
                    <p className="hero-subheading">An Chrome extension that seamlessly integrates with <spam className="gmail">Gmail</spam> and <span className="whatsapp">Whatsapp Web</span>.
                        Generate smart, real-time AI replies right where you type.</p><br />
                    {/* <img src={download} height={40}/> */}
                    <div className="hero-buttons">
                        <div className="hero-extension"> Get Extension <svg width="50" height="50" viewBox="0 0 6 6" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path d="M1.25215 5.54731L0.622742 4.9179L3.78169 1.75597H1.3834L1.38936 0.890915H5.27615V4.78069H4.40513L4.41109 2.38538L1.25215 5.54731Z" />
                        </svg>
                        </div>
                        <a href="https://github.com/Harishsargar/UttarAI" target="_blank"><div className="hero-extension"> Github <svg width="50" height="50" viewBox="0 0 6 6" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path d="M1.25215 5.54731L0.622742 4.9179L3.78169 1.75597H1.3834L1.38936 0.890915H5.27615V4.78069H4.40513L4.41109 2.38538L1.25215 5.54731Z" />
                        </svg>
                        </div></a>
                    </div>

                </div>
                <div className="hero-image">
                    <img src={robo} height={450} width={450} />
                    {/* <img src={img1} height={450} width={450}/>
                <img src={img1} height={450} width={450}/>
                <img src={img1} height={450} width={450}/> */}
                </div>
            </div>
            <hr />
        </>
    )
}

export default Hero;