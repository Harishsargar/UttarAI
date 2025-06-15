

function RegisterForm(){
    return(
        <div>
            <form>
                <label>Enter Name</label>
                <input type="text" required/>
                <label>Enter email</label>
                <input type="email"/>
                <label>Enter phone number</label>
                <input type="number"/>
                <label>Choose gender</label>
                <div>
                    <input type="radio" name="gender" value="male"/>
                    <label>Male</label>
                    <input type="radio" name="gender" value="female"/>
                    <label>Female</label>
                </div>
                
                <label>Enter password</label>
                <input type="password"/>
                <label>Confirm password</label>
                <input type="password"/>
            </form>
        </div>
    )

}
export default RegisterForm;