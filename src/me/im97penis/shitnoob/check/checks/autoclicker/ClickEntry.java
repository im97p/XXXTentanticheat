package me.im97penis.shitnoob.check.checks.autoclicker;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClickEntry {

	private final long time;
	private int lastClick4, lastClick3, lastClick2, lastClick1, lastClick, currentClick;

	public void shift(int newClick) {
		this.lastClick4 = lastClick3;
		this.lastClick3 = lastClick2;
		this.lastClick2 = lastClick1;
		this.lastClick1 = lastClick;
		this.lastClick = currentClick;
		this.currentClick = newClick;
	}

	@Override
	public String toString() {
		return "lc4=" + lastClick4 + ", lc3=" + lastClick3 + ", lc2=" + lastClick2 + ", lc1=" + lastClick1 + ", lc="
				+ lastClick + ", cc=" + currentClick + ", average=" + this.getAverage();
	}

	public boolean allAbove1() {
		return lastClick2 > 1 && lastClick1 > 1 && lastClick > 1 && currentClick > 1;
	}

	public boolean allSame() {
		return addTogether() == (currentClick * 6);
	}
	
	private int addTogether() {
		return lastClick4 + lastClick3 + lastClick2 + lastClick1 + lastClick + currentClick;
	}
	
	public int getAverage() {
		return allAbove1() ? addTogether() / 6 : currentClick;
	}

}
