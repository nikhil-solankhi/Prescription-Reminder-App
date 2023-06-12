import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

const SignUp = () => {
  let navigate = useNavigate();

  const [userReg, setUserReg] = useState({ firstName: "", lastName: "", email: "", password: "", contactNo: ""});



  const handleRegister = (e) => {

    e.preventDefault();
    axios.post("http://localhost:8080/users" , userReg).then(res=>{
      console.log(res.data);
      navigate("/login");
    })
  }
  console.log(userReg);
  return <div>
    <h1>Sign Up</h1>
    
      <form onSubmit={handleRegister}>
        <div className="form-group">
          <label htmlFor="firstName">First name</label>
          <input type="text" className="form-control" required name="firstName" placeholder="Enter first name" onChange={e => (setUserReg({ ...userReg, ["firstName"]: e.target.value }))}></input>
        </div>
        <div className="form-group">
          <label htmlFor="lastName">Last name</label>
          <input type="text" className="form-control" required name="lastame" placeholder="Enter last name" onChange={e => (setUserReg({ ...userReg, ["lastName"]: e.target.value }))}></input>
        </div>
        <div className="form-group">
          <label htmlFor="email">Email address</label>
          <input type="email" className="form-control" required name="email" placeholder="Enter email" onChange={e => (setUserReg({ ...userReg, ["email"]: e.target.value }))}></input>
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input type="password" className="form-control" required name="password" placeholder="Enter password" onChange={e => (setUserReg({ ...userReg, ["password"]: e.target.value }))}></input>
        </div>
        <div className="form-group">
          <label htmlFor="contactno">Mobile No</label>
          <input type="text" className="form-control" required name="contactno" minLength={10} maxLength={10} placeholder="Enter Mobile number" onChange={e => (setUserReg({ ...userReg, "[contactNo]": e.target.value }))}></input>
        </div>
        <br></br>
        <button type="submit" className="btn btn-primary">Sign Up</button>
      </form>
      <Link to="/login" className="navLinks"><button className="btn btn-info">Sign In</button></Link>
  
      <div id="messageRegDiv"></div>
      </div>
      
}

export default SignUp;