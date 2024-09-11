import axios from 'axios'
import React, { useState } from 'react'
import '../Styles/AdminDashboard.css'

export default function AdminDashboard() {
  let[from_location, setfrom] = useState("")
  let [to_location,setto] = useState("")
  let [dateOfDept,setdate] = useState("")

  let [buses,setbuses] = useState([])

  function searchBus(e){
    e.preventDefault();
    axios.get(`http://localhost:8080/api/buses?from_location=${from_location}&to_location=${to_location}&dateOfDept=${dateOfDept}`)
    .then((res)=>{
      console.log(res.data.data);
      setbuses(res.data.data)
    })
    .catch((err)=>{
      console.log(err);
    })
  }
  return (
    <div className="admindashboard">
         <form onSubmit={searchBus} action="">
          <input type="text" placeholder='Enter the from location' required value={from_location} onChange={(e)=>{setfrom(e.target.value)}} />
          <input type="text" placeholder='Enter the to location' required value={to_location} onChange={(e)=>{setto(e.target.value)}} />
          <input type="date" required value={dateOfDept} onChange={(e)=>{setdate(e.target.value)}} />
          <button>Search</button>
         </form>
        {buses.map((item)=>{
        return(
          <div className="bus_list">
          <h4>Bus Name: {item.bus_name}</h4> &nbsp;                
                <span>From : <b>{item.from_location}&nbsp;</b></span><br />
                <span>To :<b> {item.to_location}&nbsp;</b></span><br />
                <span>Date : <b>{item.dateOfDept}&nbsp;</b></span><br />
                <span>Bus Number : <b>{item.bus_number}&nbsp;</b></span><br />
                <i>Seats : {item.noOfSeats}&nbsp;</i><br />
                <button className='btn btn-danger'>Book Ticket</button>
        </div>
        )
        })}
    </div>
  )
}
