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


public class Appactivate {
    static {
        AppActivate("Microsoft Word");                                                               //   (2) AppActivate "Microsoft Word"
        Shell("C:\\WINDOWS\\NOTEPAD.EXE", 1);                                                        //   (4) Shell "C:\WINDOWS\NOTEPAD.EXE", 1
        AppActivate("Visual Basic", 1);                                                              //   (6) AppActivate "Visual Basic", 1
        AppActivate("Visual Basic", 1);                                                              //   (7) AppActivate "Visual Basic", 1 
        AppActivate("Visual Basic", 1);                                                              //   (8) AppActivate "Visual Basic",1 
    }
}

