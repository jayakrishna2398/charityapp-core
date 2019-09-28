package util;

public class Logger {
	   public static Logger logger=null;
	   private Logger() {}
	   public static Logger getInstance()
	   {
	   if(logger==null)
	       {    
	       logger=new Logger();
	       }    
	   return logger;    
	   }
	   public static void info(Object message)
	   {
	   System.out.println(message);
	   }
	   public static void debug(Object message)
	   {
	   System.out.println(message);
	   }
	   public static void error(String message)
	   {
	   System.err.println(message);
	   }
	   public static void error(Throwable t)
	   {
	   t.printStackTrace();
	   }
}
