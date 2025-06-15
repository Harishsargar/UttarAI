import '../../style/loginform.css'

function LoginForm(){

return(
    <div className='form-container'>
        <form className='login-form'>
            <label>Enter Email </label>
            <input type="email" required/>
            <label>Enter Password </label>
            <input type="password" required/>
            <button type="submit">submit</button>
        </form>
    </div>
)
}

export default LoginForm;