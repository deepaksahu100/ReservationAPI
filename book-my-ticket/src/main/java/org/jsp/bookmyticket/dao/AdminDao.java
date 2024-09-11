package org.jsp.bookmyticket.dao;

import java.util.Optional;

import org.jsp.bookmyticket.model.Admin;
import org.jsp.bookmyticket.repository.AdminRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Repository;

@Repository 
public class AdminDao {
	@Autowired
	private AdminRepository adminRepository;

	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	public Optional<Admin> findById(int id) {
		return adminRepository.findById(id);
	}

	public Optional<Admin> verify(long phone, String password) {
		return adminRepository.findByPhoneAndPassword(phone, password);
	}

	public Optional<Admin> verify(String email, String password) {
		return adminRepository.findByEmailAndPassword(email, password);
	}

	public void delete(int id) {
		adminRepository.deleteById(id);
	}

	public Optional<Admin> findByToken(String token) {
		return adminRepository.findByToken(token);
	}

	
}
