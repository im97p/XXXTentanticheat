package me.im97penis.shitnoob.profile;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import me.im97penis.shitnoob.check.violation.Violation;

@Getter
@Setter
public class Profile {

	private final UUID uniqueId;
	private final Set<Violation> violations;
	private boolean alerts;
	private long lastMovement;
	private int currentComboSize;
	
	public Profile(UUID uniqueId) {
		this.uniqueId = uniqueId;
		this.violations = new HashSet<>();
		this.setAlerts(true);
	}
	
	public void addViolation(Violation violation) {
		violations.add(violation);
	}
	
}
