    import '../../style/registerform.css'
    import { registerUser } from '../../api/auth';
    import { useState } from 'react';

    function RegisterForm(){
        const [formData, setFormData] = useState({
            name:"",
            email:"",
            phoneNumber:"",
            gender:"",
            password:"",
            confirmData:""
        })

        const handleChange =(e)=>{
            const {name, value} = e.target;
                setFormData((prev)=>({
                    ...prev,
                    [name]:value,
                }));
            
        }





        return(
            <div>
                <form className='register-form'>
                    <label>Enter Name</label>
                    <input type="text" value={formData.name} required/>
                    <label>Enter email</label>
                    <input type="email" value={formData.email} required/>
                    <label>Enter phone number</label>
                    <input type="number" value={formData.phoneNumber} required/>
                    <label>Choose gender</label>
                    <div>
                        <input type="radio" name="gender" value="male" checked={formData.gender=="male"} required/>
                        <label>Male</label>
                        <input type="radio" name="gender" value="female" checked={formData.gender=="female"} required/>
                        <label>Female</label>
                    </div>
                    
                    <label>Enter password</label>
                    <input type="password" name='password' value={formData.password} required/>
                    <label>Confirm password</label>
                    <input type="password" name='confirmPassword' value={formData.confirmData} required/>
                    <button type='submit'>Register</button>
                </form>
            </div>
        )

    }
    export default RegisterForm;