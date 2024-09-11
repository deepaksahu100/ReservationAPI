import axios from 'axios'
import React, { useEffect } from 'react'
import { useState } from 'react'
import { useParams } from 'react-router-dom'
import '../Styles/Editbus.css'

export default function Editbus() {
    
  let [bus_name, setbusname] = useState("")
  let [bus_number, setbusnumber] = useState("")
  let [dateOfDept, setdept] = useState("")
  let [from_location, setfromlocation] = useState("")
  let [to_location, settolocation] = useState("")
  let [noOfSeats, setseats] = useState("")

  let params = useParams();

  useEffect(()=>{
    axios.get(`http://localhost:8080/api/buses/${params.id}`)
    .then((res)=>{
        console.log(res.data.data);
        setbusname(res.data.data.bus_name);
        setbusnumber(res.data.data.bus_number)
        setfromlocation(res.data.data.from_location)
        settolocation(res.data.data.to_location)
        setdept(res.data.data.dateOfDept)
        setseats(res.data.data.noOfSeats)
    })
  },[params.id])

  let editData = {
    bus_name, bus_number, dateOfDept, from_location, to_location, noOfSeats
  }

  function editBuses(e){
    e.preventDefault()
    axios.put(`http://localhost:8080/api/buses/${params.id}`, editData)
    .then((res)=>{
      console.log(res);
      alert('Bus Edited successfully')
    })
    .catch((err)=>{
      console.log(err);
      alert('Invalid input')
    })
  }

  return (
    <div className="editbus">
          <form onSubmit={editBuses} action="">
            <label htmlFor="">Bus Name:
            </label>
            <input type="text" value={bus_name} onChange={(e)=>{setbusname(e.target.value)}} required placeholder='Enter the bus name' />
            <label htmlFor="">Bus Number:
            </label>
            <input type="text" value={bus_number} onChange={(e)=>{setbusnumber(e.target.value)}} required placeholder='Enter the bus number' />
            <label htmlFor="">Date of Departure:
            </label>
            <input type="date" value={dateOfDept} onChange={(e)=>{setdept(e.target.value)}} required placeholder='Enter the date of departure' />
            <label htmlFor="">From Location:
            </label>
            <input type="text" value={from_location} onChange={(e)=>{setfromlocation(e.target.value)}} required placeholder='Enter the from location' />
            <label htmlFor="">To Location:
            </label>
            <input type="text" value={to_location} onChange={(e)=>{settolocation(e.target.value)}} required placeholder='Enter the to location' />
            <label htmlFor="">Number of Seats:
            </label>
            <input type="text" value={noOfSeats} onChange={(e)=>{setseats(e.target.value)}} required placeholder='Enter the seats' />
            <div className="editbusbtn">
                <button className='btn btn-danger'>Add Bus</button>
            </div>
        </form>
    </div>
  )
}
