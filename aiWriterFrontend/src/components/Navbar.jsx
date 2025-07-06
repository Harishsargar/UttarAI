import "../style/navbar.css";
import navImg from "../../default_icon1.png";
import { useNavigate, Link } from "react-router-dom";
import { useState, useEffect } from "react";

function Navbar() {
    const navigate = useNavigate();
    const [buttonName, setButtonName] = useState("");

    useEffect(() => {
        if (localStorage.getItem("token")) {
            setButtonName("Logout");
        } else {
            setButtonName("Login");
        }
    }, []);

    const handleLogout = () => {
        localStorage.removeItem("token");
        // setMessage("User logout successfully !!");
        setButtonName("Login");
        navigate("/home");
    };

    const handlelogin = () => {
        navigate("/login");
    };
    return (
        <div className="navbar-component">
            <div className="navbar-elements">
                <div className="navbar-right-element">
                    <Link to={"/home"}><img src={navImg} height={25} width={25} /></Link>
                    <h3><Link to={"/home"}>Uttar-AI</Link></h3>
                </div>
                <ul className="navbar-left-element">
                    <li className="navbar-left-element-link">
                        <Link to={"/emailReplyer"}>Email Replyer</Link>
                    </li>
                    {buttonName == "Login" ? (
                        <li>
                            <button onClick={handlelogin}>Login</button>
                        </li>
                    ) : (
                        <li>
                            <button onClick={handleLogout}>Logout</button>
                        </li>
                    )}
                </ul>
            </div>
            <hr className="navbar-below-line" />
        </div>
    );
}

export default Navbar;
