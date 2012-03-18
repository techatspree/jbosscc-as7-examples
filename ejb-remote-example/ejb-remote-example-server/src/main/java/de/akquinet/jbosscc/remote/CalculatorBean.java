package de.akquinet.jbosscc.remote;

import javax.ejb.Stateless;

@Stateless(name="calculator")
public class CalculatorBean implements Calculator {

	@Override
	public int add(int x, int y) {
		return x + y;
	}

	@Override
	public int subtract(int x, int y) {
		return x - y;
	}

}
