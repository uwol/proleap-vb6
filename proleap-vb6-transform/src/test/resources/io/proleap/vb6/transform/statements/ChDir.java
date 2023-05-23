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


public class Chdir {
    static {
        ChDir("D:\\TMP");                                                                            //   (1) ChDir "D:\TMP"
        String Path = null;                                                                          //   (3) Dim Path As String
        Path="C:\\Tmp";                                                                              //   (4) Path = "C:\Tmp"
        ChDir(Path);                                                                                 //   (5) ChDir Path
        ChDir("c:\\" + "Tmp");                                                                       //   (7) ChDir "c:\" & "Tmp"
    }
}

