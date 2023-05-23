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


public class Beep {
    static {
        Beep();                                                                                      //   (1) Beep
        Integer J = null;                                                                            //   (3) Dim J
        for(J = 1; J <= 2; J++){                                                                     //   (4) For J = 1 To 2
            Beep();                                                                                  //   (5)    Beep
        }
    }
}

