import React from 'react'
import Dropdown1 from './Dropdown1'
import '../Styles/AdminNavbar.css'



export default function AdminNavbar() {
  return (
    <div className="adminnavbar">
        <div className="logo">
            <h3>BOOK<span>myTICKET</span></h3>
            {/* <img src="bookmyticketlogo" alt="not available" /> */}
        </div>
        <div className="account">
            <Dropdown1/>
        </div>
    </div>
  )
}
