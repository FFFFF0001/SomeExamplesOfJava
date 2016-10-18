package cn.itcast.bookstore.exception;

public class RegisterException extends Exception{
	public RegisterException(){
		super();
	}
	public RegisterException(String message){
		super(message);
		
	}
	public RegisterException(Throwable cause){
		super(cause);
		
	}
	public RegisterException(String message,Throwable cause){
		super(message,cause);
	}
	
}