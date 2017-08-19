package devopsbuddy.backend.service;



import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import devopsbuddy.backend.peristence.domain.backend.Plan;
import devopsbuddy.backend.peristence.domain.backend.User;
import devopsbuddy.backend.peristence.domain.backend.UserRole;
import devopsbuddy.backend.peristence.repositories.PlanRepository;
import devopsbuddy.backend.peristence.repositories.RoleRepository;
import devopsbuddy.backend.peristence.repositories.UserRepository;
import devopsbuddy.enums.PlansEnum;

@Service
@Transactional(readOnly = true)
public class UserService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user, PlansEnum planEnum, Set<UserRole> userRoles) {
		
		Plan plan = new Plan(planEnum);
		
		if(!planRepository.exists(planEnum.getId())) {
			plan = planRepository.save(plan);
		}
		
		user.setPlan(plan);
		
		for (UserRole userRole : userRoles) {
			roleRepository.save(userRole.getRole());
		}
		
		user.getUserRoles().addAll(userRoles);
		
		user = userRepository.save(user);
		
		return user;
		
	}
}
