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


public class Width {
    static {
        Object I = null;                                                                             //   (1) Dim I
        Open("c:/test.csv", new VbFileNumber("#1"));                                                 //   (2) Open "c:/test.csv" For Output As #1
                                                                                                     //   (3) Width #1, 10
        new VbFileNumber("#1")10print(new VbFileNumber("#1"), 123, 456);                             //   (4) Print #1, 123 , 456
        Close();                                                                                     //   (5) Close
    }
}

