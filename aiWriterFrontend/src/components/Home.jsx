import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import Navbar from "./Navbar";
import Hero from "./Hero";
import Usecase from "./Usecase";

function Home() {
  const [message, setMessage] = useState("");

  const handleLogout = () => {
    localStorage.removeItem("token");
    setMessage("User logout successfully !!");
  };

  return (
    <>
    <Navbar/>
    <Hero/>
    <Usecase/>
    </>
  );
}

export default Home;
