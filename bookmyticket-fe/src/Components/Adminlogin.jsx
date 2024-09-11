import React, { useState } from 'react'
import '../Styles/Adminlogin.css'
import { Link, useNavigate } from 'react-router-dom'
import axios from 'axios'

export default function Adminlogin() {

  let [email,setemail]= useState("")
  let [password, setpassword]= useState("")
  let navigate = useNavigate();

  function verifyByEmail(e){
    e.preventDefault();
    axios.post(`http://localhost:8080/api/admins/verify-by-email?email=${email}&password=${password}`)
    .then((res)=>{
      navigate('/adminhomepage')
      alert("Verification Successful")
      console.log(res.data.data)
      localStorage.setItem("Admin", JSON.stringify(res.data.data))
    })
    .catch((err)=>{
      alert("Login Failed")
      console.log(err);
    })
    
  }
 
  return (
   <div className="adminlogin">
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
        <p>Are you new here? <Link to='/adminsignup'>Register here....</Link></p>
     </form>
     
  
   </div>
  )
}
