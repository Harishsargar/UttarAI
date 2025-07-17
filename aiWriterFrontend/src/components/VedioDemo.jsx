import "../style/videodemo.css";

function VedioDemo() {


    return (
        <>
            <div className="video-component">
                <div className="video-div">
                    <h2>Uttar-AI in Action</h2>
                    <div className="video">
                        <iframe
                            width="500"
                            height="250"
                            src="https://www.youtube.com/embed/0Ut9IkTiQDs"
                            title="YouTube video player"
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                            allowFullScreen
                        ></iframe>
                    </div>
                </div>
                <div className="video-div">
                    <h2>Payment Gateway Integration</h2>
                    <div className="video">
                        <iframe
                            width="500"
                            height="250"
                            src="https://www.youtube.com/embed/zUCGtyE4uKg"
                            title="YouTube video player"
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                            allowFullScreen
                        ></iframe>
                    </div>
                </div>
            </div>
        </>
    );
}

export default VedioDemo;
