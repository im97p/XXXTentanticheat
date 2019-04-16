package me.im97penis.shitnoob.check.violation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.im97penis.shitnoob.check.Check;

@Getter
@AllArgsConstructor
public class Violation {

	private final Check violatedCheck;
	private final long time;
	
}
