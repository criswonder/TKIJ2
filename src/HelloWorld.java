import org.apache.tools.ant.Task;
import org.apache.tools.ant.BuildException;

public class HelloWorld extends Task {

    String message;
    public void setMessage(String msg) {
        message = msg;
    }
    public void addText(String text) {
        message = getProject().replaceProperties(text);
    }


    public void execute() {
        if (message==null) {
            throw new BuildException("No message set.");
        }
        log(message);
    }

}

