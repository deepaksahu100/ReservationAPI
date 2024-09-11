import React from 'react'
import AdminNavbar from '../Components/AdminNavbar.jsx'
import AdminDashboard from '../Components/AdminDashboard.jsx'
import {Routes, Route } from 'react-router-dom'
import AddBus from './AddBus.jsx'
import ViewBus from './ViewBus.jsx'
import Editbus from './Editbus.jsx'

export default function AdminHomePage() {


  return (
    <div className="adminhomepage">
        <AdminNavbar/>
        <Routes>
            <Route path='/' element={<AdminDashboard/>}/>
            <Route path='/addbus' element={<AddBus/>}/>
            <Route path='/viewbus' element={<ViewBus/>}/>
            <Route path='/editbus/:id' element={<Editbus/>}/>
        </Routes>
    </div>
  )
}
