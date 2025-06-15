import '../../style/registerform.css'

function RegisterForm(){
    return(
        <div className='form-container'>
            <form className='register-form'>
                <label>Enter Name</label>
                <input type="text" required/>
                <label>Enter email</label>
                <input type="email" required/>
                <label>Enter phone number</label>
                <input type="number" required/>
                <label>Choose gender</label>
                <div>
                    <input type="radio" name="gender" value="male" required/>
                    <label>Male</label>
                    <input type="radio" name="gender" value="female" required/>
                    <label>Female</label>
                </div>
                
                <label>Enter password</label>
                <input type="password" required/>
                <label>Confirm password</label>
                <input type="password" required/>
                <button type='submit'>Register</button>
            </form>
        </div>
    )

}
export default RegisterForm;