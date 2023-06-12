import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const Login = () => {
  let navigate = useNavigate();
  const [userLogin, setUserLogin] = useState({ email: "", password: ""});


  const handleLogin = (e) => {

    e.preventDefault();
    console.log(userLogin);

    axios.post("http://localhost:8080/users/signin" , userLogin).then(res=>{
      console.log(res.data);
      localStorage.setItem("user", JSON.stringify(res.data));
    navigate("/prescriptionDisplay");
    })
   
  }

  const onForgetPasswod=(e)=>{
    navigate("/forgetpassword");
  }



  return <div >
    
    <form onSubmit={handleLogin}>
      <div className="form-group">
        <label htmlFor="exampleInputEmail1">Email address</label>
        <input type="email" className="form-control" placeholder="Enter email" onChange={e => (setUserLogin({ ...userLogin, ["email"]: e.target.value }))}></input>
        <small id="emailHelp" className="form-text text-muted">We'll never share your email with anyone else.</small>
      </div>
      <br></br>
      <div className="form-group">
        <label htmlFor="exampleInputPassword1">Password</label>
        <input type="password" className="form-control" placeholder="Password" onChange={e => (setUserLogin({ ...userLogin, ["password"]: e.target.value }))}></input>
      </div>
      <br></br>
      
      <button type="submit" className="btn btn-primary" >Login</button>
    </form>
   <button type="submit" className="btn btn-primary" onClick={onForgetPasswod} >Forgot Password</button>
    <div id="messageRegDiv"></div>
  </div>
}

export default Login;