import java.util.Collection;
import java.util.Date;

import static io.proleap.vb6.api.App.*;
import static io.proleap.vb6.api.CastUtils.*;
import static io.proleap.vb6.api.Constants.*;
import static io.proleap.vb6.api.Debug.*;
import static io.proleap.vb6.api.Err.*;
import static io.proleap.vb6.api.Functions.*;

import io.proleap.vb6.api.adodb.*;
import io.proleap.vb6.api.com.*;
import io.proleap.vb6.api.forms.*;
import io.proleap.vb6.api.primitives.*;


public class Openclose {
    static {
        Open(Filename, new VbFileNumber("#A"));                                                      //   (1) Open Filename For Output As #A
        Open("c:\\Tmp\\File", new VbFileNumber("#2"));                                               //   (2) Open "c:\Tmp\File" FOR BiNaRy AS #2
        Open("c:/Tmp/File2", new VbFileNumber("#B"), 2);                                             //   (3) Open "c:/Tmp/File2" FOR Append ACCESS READ WRITE LOCK READ WRITE AS #B LEN= 2
        String tmp = null;                                                                           //   (5) Dim tmp as String, content as String
        String content = null;                                                                      
                                                                                                     //   (6) Line Input #A, tmp
        Close(new VbFileNumber("#A")new VbFileNumber("#A"));                                         //   (8) Close #A
        Close();                                                                                     //   (9) Close
        Open("C:\\file3", new VbFileNumber("#1"));                                                   //  (12) Open "C:\file3" For Input as #1
        while(toInt(Eof(1)) == 0){                                                                   //  (13) While Eof(1) = 0
                                                                                                     //  (14) 	line input #1, tmp
            content=String.valueOf(content + tmp);                                                   //  (15) 	content = content + tmp
        }
                                                                                                     //  (16) Wend
        Close(new VbFileNumber("#1")new VbFileNumber("#1"));                                         //  (17) Close #1
    }
    
    private static void save(){                                                                      //  (20) Private Sub save()
        Open("test" + ".dat", new VbFileNumber("#1"));                                               //  (21) 	Open "test" & ".dat" For Output As #1
        Save();                                                                                      //  (22) 	Save
    }
}

