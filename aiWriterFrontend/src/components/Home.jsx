import { useNavigate,Link } from "react-router-dom";

function Home(){

    return(
        <>
            <h1>HOME PAGE</h1>
            <Link to={'/login'}><button>Login</button></Link>
        
        </>
    )
}

export default Home;