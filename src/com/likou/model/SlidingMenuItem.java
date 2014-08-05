package com.likou.model;

import android.content.Intent;

public class SlidingMenuItem {

	public String name;
	public int icon;
	public Intent intent;

	public SlidingMenuItem(String name, int icon, Intent intent) {
		this.name = name;
		this.icon = icon;
		this.intent = intent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + icon;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SlidingMenuItem other = (SlidingMenuItem) obj;
		if (icon != other.icon)
			return false;
		return true;
	}

}
