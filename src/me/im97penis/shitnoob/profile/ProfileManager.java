package me.im97penis.shitnoob.profile;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ProfileManager {

	private final Set<Profile> profiles;

	public ProfileManager() {
		this.profiles = new HashSet<>();
	}
	
	public Profile getById(UUID id) {
		return profiles.stream().filter(profile -> profile.getUniqueId().equals(id)).findAny().orElseGet(() -> {
			Profile profile = new Profile(id);

			profiles.add(profile);

			return profile;
		});
	}

}
