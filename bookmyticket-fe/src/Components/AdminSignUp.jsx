import React, { useState } from 'react'
import '../Styles/AdminSignUp.css'
import axios from 'axios'

export default function AdminSignUp() {
    let [name,setname] = useState("")
    let [phone,setphone] = useState("")
    let [gst_number,setgstno] = useState("")
    let [email,setemail] = useState("")
    let [travels_name,settravels] = useState("")
    let [password,setpassword] = useState("")

    let data = {
      name,phone,gst_number,email,travels_name,password
    }

    function createAdmin(e){
      e.preventDefault();
      axios.post('http://localhost:8080/api/admins',data)
      .then((res)=>{
        alert("Data Added successfully");
        console.log(res);
      })
      .catch((err)=>{
        alert("Invalid inputs, unable to add data");
        console.log(err);
      })
    }
  return (
    <div className="adminsignup">
        <form onSubmit={createAdmin} action="">
        <div className="adminheading">
            <h2>Register here for Admin</h2>
        </div>
        <div></div>
            <label htmlFor="">Username:</label>
            <input type="text" value={name} onChange={(e)=>{setname(e.target.value)}} placeholder='Enter your name' required />
            <label htmlFor="">Phone:</label>
            <input type='tel' pattern='[0-9]{10}'  value={phone} onChange={(e)=>{setphone(e.target.value)}} placeholder='Enter your number' required />
            <label htmlFor="">Email:</label>
            <input type='email'  value={email} onChange={(e)=>{setemail(e.target.value)}} placeholder='Enter the Email' required />
            <label htmlFor="">Travels Name:</label>
            <input type='text'  value={travels_name} onChange={(e)=>{settravels(e.target.value)}} placeholder='Enter the Travels name' required />
            <label htmlFor="">Gst Number:</label>
            <input type="text"  value={gst_number} onChange={(e)=>{setgstno(e.target.value)}} placeholder='Enter the Gst Number' required />
            <label htmlFor="">Password:</label>
            <input type='text'  value={password} onChange={(e)=>{setpassword(e.target.value)}} placeholder='Enter the password' required />
           <div className="btnadmin">
           <button className='btn btn-danger'>Register</button>
           </div>
            
        </form>
    </div>
  )
}
