package s2ooadoop.kea.services;

import org.springframework.stereotype.Service;


/**
 * The Logging server is used for logging comments, and is used to give easy access to switch from 1 logging module to another
 * During testing, logging is done to the command line
 */
@Service
public class Logging {
    String history = "";
    String callerclass = "";

    public Logging()
    {
        this.callerclass = "";
    }

    public Logging(String callerclass)
    {
        this.callerclass = callerclass + " > ";
    }
    public void log(String str)
    {
        log(str, 0);
    }

    public void log(String str, int indent)
    {
        String s = "";
        for(int i = 0; i < indent;i++)
        {
            s = s + "\t";
        }
        s = s + str;
        history = history + "\n" + callerclass + s;
    }

    public String getSessionLog(){
        return(history);
    }
}
