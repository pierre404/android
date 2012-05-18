package com.exia.algoant;

public class AntException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int TO_DELETE = 4;
	public final static int TO_REGISTER = 5;

	public int status;
	public Ant a;
}
