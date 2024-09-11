import React, { useState } from 'react'
import '../Styles/Usersignup.css'
import axios from 'axios'

export default function Usersignup() {

    let [name,setname] = useState("")
    let [email,setemail] = useState("")
    let [phone,setphone] = useState("")
    let [age,setage] = useState("")
    let [gender,setgender] = useState("")
    let [password,setpassword] = useState("")

    let data = {
        name, email, phone, age, gender, password
    }

    function createUser(e){
        e.preventDefault();
        axios.post('http://localhost:8080/api/users',data)
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
    <div className="usersignup">
        <form onSubmit={createUser} action="">
        <div className="userheading">
            <h2>Register here for User</h2>
        </div>
        <div></div>
            <label htmlFor="">Name:</label>
            <input type="text" value={name} onChange={(e)=>setname(e.target.value)} required placeholder='Enter the name' />
            <label htmlFor="">Email:</label>
            <input type="email" value={email} onChange={(e)=>setemail(e.target.value)} required placeholder='Enter the Email' />
            <label htmlFor="">Phone:</label>
            <input type="tel" value={phone} onChange={(e)=>setphone(e.target.value)} required placeholder='Enter the phone' />
            <label htmlFor="">Age:</label>
            <input type="number" value={age} onChange={(e)=>setage(e.target.value)}  required placeholder='Enter the age' />
            <label htmlFor="">Gender:</label>
            <input type="text" value={gender} onChange={(e)=>setgender(e.target.value)}  required placeholder='Enter the gender' />
            <label htmlFor="">Password:</label>
            <input type="text" value={password} onChange={(e)=>setpassword(e.target.value)}  required placeholder='Enter the password' />
            <div className="usersignupbtn">
                <button className='btn btn-danger'>Register</button>
            </div>
        </form>
    </div>
  )
}
