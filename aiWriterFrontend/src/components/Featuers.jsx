import "../style/featuers.css";

function Featuers() {
    return (
        <>
            <div className="features-component">
                <h1 className="features-title">Key Features</h1>
                <div className="features-container">
                    <div className="feature">
                        <div className="feature-title">
                            <h3>Smart AI-Generated Replies</h3>
                        </div>
                        <div className="feature-desc">
                            <p>Crafts intelligent, relevant replies using powerful AI models no manual typing needed.</p>
                        </div>
                    </div>

                    <div className="feature">
                        <div className="feature-title">
                            <h3>Context-Aware Suggestions</h3>
                        </div>
                        <div className="feature-desc">
                            <p>Understands the conversation thread to generate personalized and appropriate responses.</p>
                        </div>
                    </div>

                    <div className="feature">
                        <div className="feature-title">
                            <h3>One-Click Response Insertion</h3>
                        </div>
                        <div className="feature-desc">
                            <p>Automatically inserts the AI-generated reply into your message box just review and send.</p>
                        </div>
                    </div>

                    <div className="feature">
                        <div className="feature-title">
                            <h3>Supports Gmail & WhatsApp Web</h3>
                        </div>
                        <div className="feature-desc">
                            <p>Seamlessly integrates with both Gmail and WhatsApp Web to support your daily conversations.</p>
                        </div>
                    </div>

                    <div className="feature">
                        <div className="feature-title">
                            <h3>Lightweight & Privacy-Focused</h3>
                        </div>
                        <div className="feature-desc">
                            <p>Optimized for speed with minimal resource usage. Only necessary message content is processed, ensuring your privacy.</p>
                        </div>
                    </div>

                    <div className="feature">
                        <div className="feature-title">
                            <h3>Custom Tone & Style Settings <br/>( Coming Soon )</h3>
                        </div>
                        <div className="feature-desc">
                            <p>Choose how your replies sound formal, casual, or concise. Tailor the AI to match your communication style.</p>
                        </div>
                    </div>
                    
                </div>
            </div><hr className="section-division"/>
        </>
    )
}
export default Featuers;