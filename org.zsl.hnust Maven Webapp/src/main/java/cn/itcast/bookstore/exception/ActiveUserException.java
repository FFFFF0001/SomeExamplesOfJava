package cn.itcast.bookstore.exception;
import java.util.ArrayList;
public class ActiveUserException extends Exception{
	public ActiveUserException(){
		super();
	}
	public ActiveUserException(String message){
		super(message);
		
	}
	public ActiveUserException(Throwable cause){
		super(cause);
		
	}
	public ActiveUserException(String message,Throwable cause){
		super(message,cause);
	}
	
}
