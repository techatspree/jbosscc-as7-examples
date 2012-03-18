package de.akquinet.jbosscc.remote;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

@DeclareRoles("admin")
@Stateless(name="calculator")
public class CalculatorBean implements Calculator {


	@Override
	@RolesAllowed(value = {"admin"})
	public int add(int x, int y) {
		return x + y;
	}

	@Override
	@DenyAll
	public int subtract(int x, int y) {
		return x - y;
	}

}
