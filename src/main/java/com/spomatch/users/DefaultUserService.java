package com.spomatch.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class DefaultUserService implements UserService {

	private long nextId = 1;
	private List<User> registeredUsers = new ArrayList<>();

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
	@Override
	public User register(User toRegister) {
		
		toRegister.setId(getNextId());

		Set<ConstraintViolation<User>> violations = validator.validate(toRegister);
		
		if (violations.size() > 0)
			throw new BadInputException("회원가입 시에 잘못된 데이터가 있습니다.");
		
		// Test 코드에서 setter 호출 시 해당 서비스에서 저장한 엔터티까지 바뀌는
		// 문제 때문에 그대로 복제해서 저장 후, 원본엔 Id만 추가하여 반환
		User registered = cloneUser(toRegister);
		
		registeredUsers.add(registered);
		
		return toRegister;
	}

	private UserId getNextId() {
		return new UserId(nextId++);
	}

	@Override
	public User updateUserInfo(User toUpdate) {
		
		for (User user : registeredUsers) {
			if (user.equals(toUpdate)) {
				user.updateUserInfo(toUpdate);
				return user;
			}
		}
		
		throw new UserNotExistException();
	}

	@Override
	public User updatePlayers(User toUpdate) {
		
		for (User user : registeredUsers) {
			if (user.equals(toUpdate)) {
				user.setPlayers(toUpdate.getPlayers());
				return user;
			}
		}
		
		throw new UserNotExistException();
	}

	private User cloneUser(User toClone) {
		
		User user = new User();
		user.setId(toClone.getId());
		user.setIdForLogin(toClone.getIdForLogin());
		user.setPw(toClone.getPw());
		user.setName(toClone.getName());
		user.setPreferredLocations(toClone.getPreferredLocations());
		user.setAge(toClone.getAge());
		user.setPlayers(toClone.getPlayers());
		
		return user;
	}

	@Override
	public void cancel(UserId idToCancel) {
		
		User toCancel = getById(idToCancel);
		
		if (toCancel.getPlayerSize() > 0)
			throw new PlayerExistWhenCancelMembershipException();
		
		registeredUsers.removeIf(user -> user.getId().equals(idToCancel));
	}

	@Override
	public User getById(UserId id) {
		
		for (User user : registeredUsers) {
			if (user.getId().equals(id))
				return user;
		}
		
		throw new UserNotExistException();
	}
}
