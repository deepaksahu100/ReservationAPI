import Dropdown from 'react-bootstrap/Dropdown';

function Dropdown1() {
  return (
    <Dropdown>
      <Dropdown.Toggle variant="danger" id="dropdown-basic">
        Account
      </Dropdown.Toggle>

      <Dropdown.Menu>
        <Dropdown.Item href="#/action-1">Edit Profile</Dropdown.Item>
        <Dropdown.Item href="/adminhomepage/addbus">Add Buses</Dropdown.Item>
        <Dropdown.Item href="/adminhomepage/viewbus">View Buses</Dropdown.Item>
        <Dropdown.Item href="#/action-3">Log Out</Dropdown.Item>
      </Dropdown.Menu>
    </Dropdown>
  );
}

export default Dropdown1;