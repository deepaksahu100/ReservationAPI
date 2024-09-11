import React from 'react'
import { Link } from 'react-router-dom'
import '../Styles/Landingpage.css'

export default function Landingpage() {
  return (
    <div className="landingpage">
          <div className="lp1">
          <Link to="/adminlogin">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShPmMW4zYgQpmjO50llMv0x0rkzNWhqUIsnA&s" alt="" />
            <h2>Admin Login</h2>
        </Link>
          </div>
       <div className="lp1">
       <Link to="/userlogin">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQX-BH0rwTlqY-_4BGCB_EYWt0vkOJkI8aBDQ&s" alt="" />
            <h2>User login</h2>
        </Link>
       </div>
    </div>
  )
}
