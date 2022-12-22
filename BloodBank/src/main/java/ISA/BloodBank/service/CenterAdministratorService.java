package ISA.BloodBank.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ISA.BloodBank.dto.CenterAdministratorRegistrationDTO;
import ISA.BloodBank.dto.CenterAdministratorUpdateDTO;
import ISA.BloodBank.dto.ChangePasswordDTO;
import ISA.BloodBank.iservice.ICenterAdministratorService;
import ISA.BloodBank.model.Appointment;
import ISA.BloodBank.model.Authority;
import ISA.BloodBank.model.CenterAdministrator;
import ISA.BloodBank.model.MedicalCenter;
import ISA.BloodBank.model.User;
import ISA.BloodBank.model.UserType;
import ISA.BloodBank.repository.ICenterAdministratorRepository;

@Service
public class CenterAdministratorService implements ICenterAdministratorService{

	private ICenterAdministratorRepository centerAdministratorRepository;
	
	private AuthorityService authorityService;
	
	private MedicalCenterService medicalCenterService;
	
	private AppointmentService appointmentService;
	
	@Autowired
	public CenterAdministratorService(ICenterAdministratorRepository centerAdministratorRepository, AuthorityService authorityService,
			MedicalCenterService medicalCenterService, AppointmentService appointmentService) {
		this.centerAdministratorRepository = centerAdministratorRepository;
		this.authorityService = authorityService;
		this.medicalCenterService = medicalCenterService;
		this.appointmentService = appointmentService;
	}

	@Override
	public CenterAdministrator registerCenterAdministrator(
			CenterAdministratorRegistrationDTO centerAdministratorRegistrationDTO) {
		
		CenterAdministrator centerAdministrator = new CenterAdministrator();
		centerAdministrator.setEmail(centerAdministratorRegistrationDTO.getEmail());
		byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		centerAdministrator.setSalt(encodedSalt);
		String passwordWithSalt = generatePasswordWithSalt(centerAdministratorRegistrationDTO.getPassword(), encodedSalt);
		String securePassword = hashPassword(passwordWithSalt);
		centerAdministrator.setPassword(securePassword);
		centerAdministrator.setName(centerAdministratorRegistrationDTO.getName());
		centerAdministrator.setSurname(centerAdministratorRegistrationDTO.getSurname());
		centerAdministrator.setAddress(centerAdministratorRegistrationDTO.getAddress());
		centerAdministrator.setPhoneNumber(centerAdministratorRegistrationDTO.getPhoneNumber());
		centerAdministrator.setJmbg(centerAdministratorRegistrationDTO.getJmbg());
		centerAdministrator.setGender(centerAdministratorRegistrationDTO.getGender());
		centerAdministrator.setOccupation(centerAdministratorRegistrationDTO.getOccupation());
		centerAdministrator.setOccupationInfo(centerAdministratorRegistrationDTO.getOccupationInfo());
		centerAdministrator.setMedicalCenter(medicalCenterService.findById(centerAdministratorRegistrationDTO.getMedicalCenter()));
		centerAdministrator.setUserType(UserType.CENTER_ADMINISTRATOR);
		
		centerAdministrator.setEnabled(true);
		Authority authority = authorityService.findByName("ROLE_CENTER_ADMINISTRATOR");
		centerAdministrator.setAuthority(authority);
		centerAdministratorRepository.save(centerAdministrator);
		return centerAdministrator;
	}

	@Override
	public List<CenterAdministrator> getAllCenterAdministrator() {
		return centerAdministratorRepository.findAll();
	}
	
	public CenterAdministratorUpdateDTO update(CenterAdministratorUpdateDTO centerAdmDto) {
		System.out.println("nesto"); 
		CenterAdministrator centAdm = (CenterAdministrator) centerAdministratorRepository.findById(centerAdmDto.getUserId()).get();
		centAdm.setEmail(centerAdmDto.getEmail());
		centAdm.setPassword(centerAdmDto.getPassword());
		centAdm.setName(centerAdmDto.getName());
		centAdm.setSurname(centerAdmDto.getSurname());
		centAdm.setAddress(centerAdmDto.getAddress());
		centAdm.setPhoneNumber(centerAdmDto.getPhoneNumber());
		centAdm.setJmbg(centerAdmDto.getJmbg());
		centAdm.setGender(centerAdmDto.getGender());
		centAdm.setOccupation(centerAdmDto.getOccupation());
		centAdm.setOccupationInfo(centerAdmDto.getOccupationInfo());
		
		centerAdministratorRepository.save(centAdm);
		
		return centerAdmDto; 
		
	}
	
	private static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] genSalt = new byte[16];
		random.nextBytes(genSalt);
		return genSalt;
	}

	private String generatePasswordWithSalt(String userPassword, String salt) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(userPassword);
		stringBuilder.append(salt);
		return stringBuilder.toString();
	}

	public String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}
	
	public CenterAdministrator findById(Long id) throws AccessDeniedException {
		CenterAdministrator centerAdministrator = centerAdministratorRepository.findById(id).orElseGet(null);
		return centerAdministrator;
	}
	
	public CenterAdministrator findByEmail(String email) {
		return centerAdministratorRepository.findByEmail(email); 
	}
	
	public MedicalCenter getMedicalCenter(String email) {
		CenterAdministrator centAdmin = findByEmail(email); 
		
		return centAdmin.getMedicalCenter(); 
		
	}
	
	public ArrayList<CenterAdministrator> getAllAdministratorsByCenterId(Long id){
		ArrayList<CenterAdministrator> list = (ArrayList<CenterAdministrator>) getAllCenterAdministrator();	
		ArrayList<CenterAdministrator> retList = new ArrayList<CenterAdministrator>(); 
		
		for(int i =0; i < list.size();  i++) {
			if(list.get(i).getMedicalCenter().getCenterId().equals(id)) {
				retList.add(list.get(i));
			}
		}
		
		return retList; 
	}
	
	public CenterAdministrator changePassword(ChangePasswordDTO newPassword) {
		CenterAdministrator centreAdmin = findByEmail(newPassword.getEmail()); 	
		checkInput(newPassword, centreAdmin);
		generateNewSecurePassword(newPassword, centreAdmin);
		return centerAdministratorRepository.save(centreAdmin);
	}
	
	private void checkInput(ChangePasswordDTO changePasswordDTO, CenterAdministrator centreAdmin) {
		if (changePasswordDTO.getPassword().equals(changePasswordDTO.getOldPassword())) {
			throw new IllegalArgumentException("Password can not be the same as the old one.");
		}
		if (!changePasswordDTO.getPassword().equals(changePasswordDTO.getPasswordRepeated())) {
			throw new IllegalArgumentException("Passwords must match!");
		}
		if (changePasswordDTO.getPassword().isEmpty() || changePasswordDTO.getPasswordRepeated().isEmpty()
				|| changePasswordDTO.getOldPassword().isEmpty()) {
			throw new IllegalArgumentException("Fill all the required fields!");
		}
		
		
		String oldPassword = generatePasswordWithSalt(changePasswordDTO.getOldPassword(), centreAdmin.getSalt());
		if(!verifyHash(oldPassword, centreAdmin.getPassword())) {
			throw new IllegalArgumentException("Old password isn't correct!");
		}
	}
	
	private void generateNewSecurePassword(ChangePasswordDTO changePasswordDTO, CenterAdministrator centreAdmin) {
		byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		centreAdmin.setSalt(encodedSalt);
		String passwordWithSalt = generatePasswordWithSalt(changePasswordDTO.getPassword(), encodedSalt);
		String newSecurePassword = hashPassword(passwordWithSalt);
		centreAdmin.setPassword(newSecurePassword);
	}
	
	public boolean verifyHash(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}
	
	public List<CenterAdministrator>GetFreeCenterAdministartior(Long medicalCenterId, String date, String time){
		 List<CenterAdministrator> administartors = centerAdministratorRepository.findCenterAdministratorsByMedicalCenterCenterId(medicalCenterId);
		 List<Appointment> appointments = appointmentService.getAllAppointmentsByMedicalCenterIdAndDate(medicalCenterId, date, time);
		 List<CenterAdministrator> centerAdministartors = new ArrayList<CenterAdministrator>();
		 for(CenterAdministrator centerAdministrator : administartors) {
			 if(appointments.isEmpty()) {
				 centerAdministartors.add(centerAdministrator);
			 }else {
				 for(Appointment appointment : appointments) {
					 if(centerAdministrator.getUserId() != appointment.getCenterAdministrator().getUserId()) {
						 centerAdministartors.add(centerAdministrator);
					 }
				 }
			 }
		 }
		 return centerAdministartors;
	}
}
