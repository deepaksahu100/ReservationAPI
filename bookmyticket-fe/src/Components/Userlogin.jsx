import axios from 'axios';
import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import '../Styles/Userlogin.css'

export default function Userlogin() {

  
  let [email,setemail]= useState("")
  let [password, setpassword]= useState("")
  let navigate = useNavigate();

  function verifyByEmail(e){
    e.preventDefault();
    axios.post(`http://localhost:8080/api/users/verify-by-email?email=${email}&password=${password}`)
    .then((res)=>{
      navigate('/userhomepage')
      alert("Verification Successful")
      console.log(res);
    })
    .catch((err)=>{
      alert("Login Failed")
      console.log(err);
    })
    
  }
  return (
   <div className="userlogin">
     <form onSubmit={verifyByEmail} action="">
        <label htmlFor="">
            Username:
        </label>
        <input type="text" value={email} onChange={(e)=>{setemail(e.target.value)}} placeholder='Enter the Username' required />
        <label htmlFor="">
            Password:
        </label>
        <input type="text" value={password} onChange={(e)=>{setpassword(e.target.value)}} placeholder='Enter the Password' required />
        <button>Login</button> <br />
        <p>Are you new here? <Link to='/usersignup'>Register here....</Link></p>
     </form>
   </div>
  )
}
