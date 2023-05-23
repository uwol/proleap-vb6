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


public class Propertyget {
    static {
        Integer CurrentI = null;                                                                     //   (1) Dim CurrentI As Integer
    }
    
    protected static Integer ValueOfI(){                                                             //   (3) Property Get ValueOfI() As Integer
        Integer ValueOfI = null;
        ValueOfI=CurrentI;                                                                           //   (4) 	ValueOfI = CurrentI
        return ValueOfI;
    }
    static {
                                                                                                     //   (5) End Property
        Integer J = null;                                                                            //   (7) Dim J
        J=ValueOfI();                                                                                //   (8) J = ValueOfI
    }
}

