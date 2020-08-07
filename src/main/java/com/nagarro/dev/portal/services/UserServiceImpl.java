 package com.nagarro.dev.portal.services;

import java.util.List;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagarro.dev.portal.models.AdminComments;
import com.nagarro.dev.portal.models.AuthenticationResponse;
import com.nagarro.dev.portal.models.ErrorResponse;
import com.nagarro.dev.portal.models.LoginForm;
import com.nagarro.dev.portal.models.Ticket;
import com.nagarro.dev.portal.models.Users;
import com.nagarro.dev.portal.repositories.AdminCommentsRepository;
import com.nagarro.dev.portal.repositories.TicketRepository;
import com.nagarro.dev.portal.repositories.TicketSummary;
import com.nagarro.dev.portal.repositories.UsersRepository;
import com.nagarro.dev.portal.utils.JwtUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private AdminCommentsRepository commentsRepo;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private PasswordEncoder bpe;
	
	public String passcode() {
	    RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45)
	        .build();
	    return pwdGenerator.generate(length);
	}
	
	public ResponseEntity<?> loginUser(LoginForm data) throws Exception {
		try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPass())
            );
        } catch (BadCredentialsException ex) {
        	System.out.println("bad credentials");
        	return ResponseEntity.ok(new ErrorResponse("No Such User"));
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Invalid Credentials"));
        }
		
		final UserDetails user = userDetailsService.loadUserByUsername(data.getUsername());
		Users u = this.usersRepository.findByUsername(data.getUsername());
		final String jwt = jwtTokenUtil.generateToken(user);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new AuthenticationResponse(jwt,
				u.getFirstName(), u.getEmail(), u.getId()));
	}
	
	public List<Users> list() {		// Get all Users (Works)
		return usersRepository.findAll();
	}
	
	public ResponseEntity<?> createUser(Users user) {	// Add a User (Works)
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
			     .withinRange('a', 'z').build();
		String initpass = generator.generate(8);

		System.out.println(user.toString());
		user.setPassword(bpe.encode(initpass));
		
		if (usersRepository.existsByUsername(user.getUsername())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Username already exists!"));
		}
		usersRepository.save(user);
		initUsername(user.getEmail(), user.getUsername(), user.getFirstName(), initpass);
		return null;
	}
	
	public Set<TicketSummary> getUserTickets(long id) {	// Get all user tickets(Works)
		return usersRepository.getTicketsSummary(id);
	}
	
	public void createUserTicket(long uid, Ticket ticket) {	// Add a user ticket(works)
		Users user = usersRepository.getOne(uid);
		ticket.setUser(user);
		user.addTicket(ticket);
		
		usersRepository.save(user);
	}
	
	public Ticket viewUserTicketSolo(long uid, long tid) {	// view One user ticket(Works)
		Ticket t = usersRepository.getTicketOneView(tid, uid);
		t.getUser().setListOfTickets(null);
		return t;
	}
	
	public void updateUserTicket(long id, Ticket ticket) {	// Update user ticket
		ticket.setTicketId(id);
		Ticket oldTicket = ticketRepository.getOne(id);

		oldTicket.setAdditionalDetails(ticket.getAdditionalDetails());
		oldTicket.setDurationOfTravel(ticket.getDurationOfTravel());
		oldTicket.setEndDate(ticket.getEndDate());
		oldTicket.setStartDate(ticket.getStartDate());
		oldTicket.setExpenseBorneBy(ticket.getExpenseBorneBy());
		oldTicket.setFromLocation(ticket.getFromLocation());
		oldTicket.setToLocation(ticket.getToLocation());
		oldTicket.setPassportNumber(ticket.getPassportNumber());
		oldTicket.setProject(ticket.getProject());
		oldTicket.setTravelApproverName(ticket.getTravelApproverName());
		oldTicket.setPriority(ticket.getPriority());
		oldTicket.setRequestType(ticket.getRequestType());
		oldTicket.setStatus(ticket.getStatus());
		oldTicket.setUpperBoundOnAmount(ticket.getUpperBoundOnAmount());
		
		ticketRepository.save(oldTicket);
	}
	
	public ResponseEntity<?> sendMail(String username) {
		Users user;
		try {
			user = usersRepository.findByUsername(username);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Invalid Credentials"));
		}
		
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
			     .withinRange('a', 'z').build();
		String randomLetters = generator.generate(8);
		
		user.setPassword(bpe.encode(randomLetters));
		
		try {
			System.out.println(forgotPasscode(user.getEmail(), user.getUsername(), user.getFirstName(), randomLetters));
			usersRepository.save(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Error Sending Request. Try Later"));
		}
	
		return null;
	}
	
	List<AdminComments> getComments(long tid) {
		return commentsRepo.findByTicketId(tid);
	}
	
	@Autowired
    private JavaMailSender sender;
 
    public String forgotPasscode(String email, String username, String fname, String password) {
    	  String msg = "Hi "+fname+ "\n as per your request for details to access Nagarro Travel Portal"
          		+ "\n\nHere is your Username:"+username+"\nHere is your Password:"+password
          		+ "\n\nPlease contact the Travel team if you have any questions.\n\n"
          		+ "Thank You, \nNagarro Travel Team.";
    	  
    	  String subject = "Nagarro Travel Portal Forgot Password";
    	
    	try {
    		this.sendEmail(email, username, fname, password, msg, subject);
    		return "Sent Successfully";
    	} catch (Exception ex) {
    		return "Error in sending mail:" + ex;
    	}
    }
    
    public String initUsername(String email, String username, String fname, String password) {
    	
    	System.out.println("in mailer");
    	 String msg = "Hi "+fname+ ",\n you have successfully registered with us."
           		+ "\n\nHere is your Username:\n"+username+"\nHere is your initial Password:\n"+password
           		+ "\n\nPlease contact the Travel team if you have any questions.\n\n"
           		+ "Thank You, \nNagarro Travel Team.";
     	  
     	  String subject = "Nagarro Travel Portal New User Registration";
     	
     	try {
     		this.sendEmail(email, username, fname, password, msg, subject);
     		return "Sent Successfully";
     	} catch (Exception ex) {
     		return "Error in sending mail:" + ex.getStackTrace();
     	}
    }
    
    private void sendEmail(String email, String username, String fname, String password, String msg, String subject) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setTo(email);
        helper.setText(msg);
        helper.setSubject(subject);
         
        sender.send(message);
    }
	
	
}
