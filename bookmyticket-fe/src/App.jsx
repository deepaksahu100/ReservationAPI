import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Adminlogin from './Components/Adminlogin';
import Userlogin from './Components/Userlogin';
import Landingpage from './Components/Landingpage';
import AdminSignUp from './Components/AdminSignUp';
import AdminHomePage from './Components/AdminHomePage';
import PageNotFound from './Components/PageNotFound';
import Userhomepage from './Components/Userhomepage';
import Usersignup from './Components/Usersignup';
import Protected from './Components/Protected';

const  App = () => {
  return ( 
    <div className="App">
      <BrowserRouter>
      <Routes>
        <Route path='/*' element={<PageNotFound/>}/>
        <Route path='/' element={<Landingpage/>}/>
        <Route path='/adminlogin' element={<Adminlogin/>}/>
        <Route path='/userlogin' element={<Userlogin/>}/>
        <Route path='/adminsignup' element={<AdminSignUp/>}/>
        <Route path='/userlogin' element={<Userlogin/>}/>
        <Route path='/usersignup' element={<Usersignup/>}/>
        <Route path='/userhomepage' element={<Userhomepage/>}/>
        <Route path='/adminhomepage/*' element={<Protected Child={AdminHomePage}/>}/>
      </Routes>
      </BrowserRouter>
    </div>
   );
}
 
export default App;


