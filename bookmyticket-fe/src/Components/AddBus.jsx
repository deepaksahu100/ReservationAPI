import React, { useState } from 'react'
import '../Styles/AddBus.css'
import axios from 'axios'

export default function AddBus() {

  let [bus_name, setbusname] = useState("")
  let [bus_number, setbusnumber] = useState("")
  let [dateOfDept, setdept] = useState("")
  let [from_location, setfromlocation] = useState("")
  let [to_location, settolocation] = useState("")
  let [noOfSeats, setseats] = useState("")

  let busData = {
    bus_name, bus_number, dateOfDept, from_location, to_location, noOfSeats
  }

  
  // console.log(admin);

  function addBuses(e){
    e.preventDefault()
    let admin = JSON.parse(localStorage.getItem("Admin"))
    axios.post(`http://localhost:8080/api/buses/${admin.id}`, busData)
    .then((res)=>{
      console.log(res);
      alert('Bus Added successfully')
    })
    .catch((err)=>{
      console.log(err);
      alert('Invalid input')
    })
  }

  return (
    <div className="addbus">
        <form onSubmit={addBuses} action="">
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
            <div className="addbusbtn">
                <button className='btn btn-danger'>Add Bus</button>
            </div>
        </form>
    </div>
  )
}
