package met;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MetricSyntaxException extends Exception {

	  public MetricSyntaxException() { super(); }
	  
          public MetricSyntaxException(String message) {
              System.out.println("ERROR: EXPECTING \"" + message + "\"");
          }
          
          public MetricSyntaxException(String message, GUI gui)
          {
				JOptionPane.showMessageDialog(gui, message);

          }
	  
          public MetricSyntaxException(String message, Throwable cause) { 
              super(message, cause); 
          }
	  
          public MetricSyntaxException(Throwable cause) { 
              super(cause); 
          }
}
