import "../style/videodemo.css";

function VedioDemo() {
    return (
        <>
            <div className="video-component">
                <h1>Video Demo</h1>
                <div className="video">
                    <iframe
                        width="560"
                        height="315"
                        src="https://www.youtube.com/embed/0Ut9IkTiQDs" // ✅ FIXED URL
                        title="YouTube video player"
                        frameBorder="0"
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                        allowFullScreen
                    ></iframe>
                </div>
            </div>
        </>
    );
}

export default VedioDemo;
