package ru.fizteh.fivt.students.krivchansky.calculator

class unknownExpressionException extends Exception {
	public unknownExpressionException(){
		super();
	}
	public unknownExpressionException(String error){
		super(error);
	}
	public unknownExpressionException(Throwable except){
		super(except);
	}
}
