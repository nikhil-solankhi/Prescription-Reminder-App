import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

const PrescriptionDisplay = () => {
    let navigate = useNavigate();

    const [currentUserPrescriptionDisplay, setCurrentPrescriptionDisplay] = useState([]);
    const user = JSON.parse(localStorage.getItem("user"));

    const logout = () => {
        localStorage.removeItem("user");
        
        navigate("/login");

    };

    useEffect(() => {
        axios
            .get(`http://localhost:8080/prescriptions/byuserid/${user.id}`)
            .then((res) => {
                console.log(res.data);
                setCurrentPrescriptionDisplay(res.data);
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    // const deletePres = id => {
    //     axios.delete("http://localhost:8080/prescriptions/", id).then(res => {
    //   console.log(res.data);
    //   localStorage.setItem("user", JSON.stringify(res.data));
    // })

    // };


    return <div>
        <div className="container fontCursive">
            <h1>Prescription of {user.firstName}</h1> <Link to="/prescription" className="navLinks"><button className="btn btn-info">Add Prescription</button></Link>
            &nbsp;
            <button onClick={logout} className="btn btn-info">Logout</button>
            <div className="row">
                <div className="col-sm-12">

                    <table className="table table-light table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Prescription Name</th>
                                <th scope="col">Dosage</th>
                                <th scope="col">Start Date</th>
                                <th scope="col">End Date</th>
                                <th scope="col">ReminderTime</th>
                                <th scope="col">Active</th>

                            </tr>
                        </thead>
                        <tbody>
                            {
                                currentUserPrescriptionDisplay?.map((item) => (
                                    <tr key={item.id}>
                                        <td>{item.name}</td>
                                        <td>{item.dosage}</td>
                                        <td>{item.startDate}</td>
                                        <td>{item.endDate}</td>
                                        <td>{item.reminderTime}</td>
                                        <td>{item.isActive}</td>




                                    </tr>

                                ))
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
}

export default PrescriptionDisplay;