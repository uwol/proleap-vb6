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


public class Print {
    static {
        Open("c:/test.csv", new VbFileNumber("#I"));                                                 //   (1) Open "c:/test.csv" For Output As #I
        print(new VbFileNumber("#I"), );                                                             //   (3) Print #I, 'blank
        print(new VbFileNumber("#I"), "Cell 1", 123, "Cell 3", "Cell 4");                            //   (4) Print #I, "Cell 1", 123, "Cell 3", "Cell 4"
        Close();                                                                                     //   (6) Close
    }
}

