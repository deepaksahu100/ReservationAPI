package org.jsp.bookmyticket.service;

import java.util.Optional;

import org.jsp.bookmyticket.Exception.AdminNotFoundException;
import org.jsp.bookmyticket.dao.AdminDao;
import org.jsp.bookmyticket.dto.AdminRequest;
import org.jsp.bookmyticket.dto.AdminResponse;
import org.jsp.bookmyticket.dto.EmailConfiguration;
import org.jsp.bookmyticket.dto.ResponseStructure;
import org.jsp.bookmyticket.model.Admin;
import org.jsp.bookmyticket.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private LinkGeneratorService linkGeneratorService;
	
	@Autowired
	private EmailConfiguration emailConfiguration;
	
	@Autowired
	private ReservationApiMailService mailService;

	public ResponseEntity<ResponseStructure<AdminResponse>> saveAdmin(AdminRequest adminRequest,
			HttpServletRequest request) {
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		Admin admin = mapToAdmin(adminRequest);
		admin.setStatus(AccountStatus.IN_ACTIVE.toString());
		admin = adminDao.saveAdmin(admin);
		String activation_link = linkGeneratorService.getActivationLink(admin, request);
		emailConfiguration.setSubject("Activate Your Account");
		emailConfiguration.setText(
				"Dear Admin Please Activate Your Account by clicking on the following link:" + activation_link);
		emailConfiguration.setToAddress(admin.getEmail());
		structure.setMessage(mailService.sendMail(emailConfiguration));
		structure.setData(mapToAdminResponse(admin));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}

	public ResponseEntity<ResponseStructure<AdminResponse>> update(AdminRequest adminRequest, int id) {
		Optional<Admin> recAdmin = adminDao.findById(id);
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		if (recAdmin.isPresent()) {
			Admin dbAdmin = recAdmin.get();
			dbAdmin.setName(adminRequest.getName());
			dbAdmin.setEmail(adminRequest.getEmail());
			dbAdmin.setGst_number(adminRequest.getGst_number());
			dbAdmin.setPassword(adminRequest.getPassword());
			dbAdmin.setPhone(adminRequest.getPhone());
			dbAdmin.setTravels_name(adminRequest.getTravels_name());
//			dbAdmin.setId(id);
			structure.setData(mapToAdminResponse(adminDao.saveAdmin(dbAdmin)));
			structure.setMessage("Admin Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new AdminNotFoundException("Cannot Update Admin as Id is Invalid");
	}

	public ResponseEntity<ResponseStructure<AdminResponse>> findById(int id) {
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		Optional<Admin> dbAdmin = adminDao.findById(id);
		if (dbAdmin.isPresent()) {
			structure.setData(mapToAdminResponse(dbAdmin.get()));
			structure.setMessage("Admin Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Invalid Admin Id");
	}

	public ResponseEntity<ResponseStructure<AdminResponse>> verify(long phone, String password) {
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		Optional<Admin> dbAdmin = adminDao.verify(phone, password);
		if (dbAdmin.isPresent()) {
			structure.setData(mapToAdminResponse(dbAdmin.get()));
			structure.setMessage("Verification Succesfull");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Invalid Phone Number or Password");
	}

	public ResponseEntity<ResponseStructure<AdminResponse>> verify(String email, String password) {
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		Optional<Admin> dbAdmin = adminDao.verify(email, password);
		if (dbAdmin.isPresent()) {
			structure.setData(mapToAdminResponse(dbAdmin.get()));
			structure.setMessage("Verification Succesfull");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Invalid Email Id or Password");
	}

	public ResponseEntity<ResponseStructure<String>> delete(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Admin> dbAdmin = adminDao.findById(id);
		if (dbAdmin.isPresent()) {
			adminDao.delete(id);
			structure.setData("Admin Found");
			structure.setMessage("Admin deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Cannot delete Admin as Id is Invalid");
	}
	
	private Admin mapToAdmin(AdminRequest adminRequest) {
		return Admin.builder().email(adminRequest.getEmail()).name(adminRequest.getName()).phone(adminRequest.getPhone())
				.gst_number(adminRequest.getGst_number()).travels_name(adminRequest.getTravels_name()).password(adminRequest.getPassword()).build();
	}
	
	private AdminResponse mapToAdminResponse(Admin admin) {
		return AdminResponse.builder().name(admin.getName()).email(admin.getEmail()).id(admin.getId()).gst_number(admin.getGst_number())
				.password(admin.getPassword()).phone(admin.getPhone()).travels_name(admin.getTravels_name()).build();
	}
	
	public String activate(String token) {
		Optional<Admin> recAdmin = adminDao.findByToken(token);
		if (recAdmin.isEmpty())
			throw new AdminNotFoundException("Invalid Token");
		Admin dbAdmin = recAdmin.get();
		dbAdmin.setStatus("ACTIVE");
		dbAdmin.setToken(null);
		adminDao.saveAdmin(dbAdmin);
		return "Your Account has been activated";
	}
	
	
	
	
	
	
}
