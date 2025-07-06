import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import Navbar from "./Navbar";

function Home() {
  const [message, setMessage] = useState("");

  const handleLogout = () => {
    localStorage.removeItem("token");
    setMessage("User logout successfully !!");
  };

  return (
    <>
    <Navbar/>
      <h1>HOME PAGE</h1>
      {/* <button onClick={handleLogout}>Logout</button>
      <br />
      {message && <p>{message}</p>}
      <br />
      <br />
      <Link to={"/login"}>
        <button>Login</button>
      </Link>
      <br />
      <br />
      <br />
      <Link to={"/emailReplyer"}>
        <button>Email Replyer</button>
      </Link> */}
    </>
  );
}

export default Home;
