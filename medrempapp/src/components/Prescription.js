import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Prescription = () => {
  let navigate = useNavigate();


  const [prescription, setPrescription] = useState({ userId: 0, name: "", dosage: "", startDate: "", endDate: "", reminderTime: "", isActive: true });
  const user = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));
    setPrescription({ ...prescription, ["isActive"]: true})
    setPrescription({ ...prescription, ["userId"]: user.id })
}, []);
  const handlePrescription = (e) => {

    e.preventDefault();
    setPrescription({ ...prescription, ["isActive"]: true})
    setPrescription({ ...prescription, ["userId"]: user.id })
    axios.post("http://localhost:8080/prescriptions", prescription).then(res => {
      console.log(res.data);
      localStorage.setItem("user", JSON.stringify(res.data));
      navigate("/prescriptionDisplay");
    })
  }
  console.log(prescription);
  return <div>
    <h1>Add Your Prescription</h1>
    <form onSubmit={handlePrescription}>
      <div className="form-group">
        <label htmlFor="name">Name</label>
        <input type="text" className="form-control" required name="name" placeholder="Enter name" onChange={e => (setPrescription({ ...prescription, ["name"]: e.target.value }))}></input>
      </div>
      <div class="form-group">
        <label htmlFor="dosage">Dosage</label>
        <input type="text" className="form-control" required name="dosage" placeholder="Enter dosage" onChange={e => (setPrescription({ ...prescription, ["dosage"]: e.target.value }))}></input>
      </div>
      <div class="form-group">
        <label htmlFor="startDate">Start Date</label>
        <input type="date" className="form-control" required name="startDate" placeholder="Enter startDate" onChange={e => (setPrescription({ ...prescription, ["startDate"]: e.target.value }))}></input>
      </div>
      <div class="form-group">
        <label htmlFor="endDate">End Date</label>
        <input type="date" className="form-control" required name="endDate" placeholder="Enter endDate" onChange={e => (setPrescription({ ...prescription, ["endDate"]: e.target.value }))}></input>
      </div>
      <div class="form-group">
        <label htmlFor="reminderTime">Reminder Time</label>
        <input type="time" className="form-control" required name="reminderTime" placeholder="Enter reminderTime" onChange={e => (setPrescription({ ...prescription, ["reminderTime"]: e.target.value }))}></input>
      </div>

      <br></br>
      <button type="submit" className="btn btn-primary">Add</button>
    </form>


    <div id="messageRegDiv"></div>
  </div>

}

export default Prescription;