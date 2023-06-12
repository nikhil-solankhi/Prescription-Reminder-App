import logo from './logo.svg';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import SignUp from './components/SignUp';
import Login from './components/Login';
import Prescription from './components/Prescription';
import PrescriptionDisplay from './components/PrescriptionDisplay';

function App() {
  return (
    <div>
       <Routes>
      <Route path='/' element={<SignUp />} />
      <Route path='/login' element={<Login />} />
      <Route path='/prescription' element={<Prescription />} />
      <Route path='/prescriptiondisplay' element={<PrescriptionDisplay />} />
    
      </Routes>
    </div>
  );
}

export default App;
