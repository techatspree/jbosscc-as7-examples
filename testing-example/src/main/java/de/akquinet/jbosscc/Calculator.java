package de.akquinet.jbosscc;

import javax.ejb.Stateless;

@Stateless
public class Calculator {

	public int add(int y, int x){
		return y + x;
	}
}
