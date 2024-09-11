import axios from 'axios'
import React, { useEffect, useState } from 'react'
import '../Styles/ViewBus.css'
import { useNavigate } from 'react-router-dom'

export default function ViewBus() {
    let [bus,setbus] = useState([])
    let navigate = useNavigate()
    useEffect(()=>{
        axios.get('http://localhost:8080/api/buses')
        .then((res)=>{
            console.log(res);
            setbus(res.data.data)
        })
        .catch((err)=>{
            console.log(err);
        })
    },[])

    function removeBus(id){
        axios.delete(`http://localhost:8080/api/buses/${id}`)
        .then((res)=>{
            console.log(res);
            alert(`Bus has been deleted`)
        })
        .catch((err)=>{
            console.log(err);
            alert("Bus is not deleted")
        })
    }

    function editNavigate(id){
        navigate(`/adminhomepage/editbus/${id}`)
    }
  return (
    <div className="ViewBus">
        {bus.map((item)=>{
            return (
               <div className="viewbus">
                 <div className="bus_details">
                    <h4>Bus Name: {item.bus_name}</h4>                 
                    <span>From : <b>{item.from_location}</b></span><br />
                    <span>To :<b> {item.to_location}</b></span><br />
                    <span>Date : <b>{item.dateOfDept}</b></span><br />
                    <span>Bus Number : <b>{item.bus_number}</b></span><br />
                    <i>Seats : {item.noOfSeats}</i><br />
                    <button className='btn btn-primary' onClick={()=>{editNavigate(item.id)}}>Edit</button>
                    <button className='btn btn-danger' onClick={()=>{removeBus(item.id)}}>Delete</button>
                </div>
               </div>
            )
        })}
    </div>
  )
}
