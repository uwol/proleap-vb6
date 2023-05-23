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


public class Function {
    
    protected static Integer Mult(Integer Factor1, Integer Factor2){                                 //   (2) Function Mult(ByVal Factor1 As Integer, Factor2 As Integer)
        Integer Mult = null;
                                                                                                     //   (3) 	' some comment
        Mult=Factor1 * Factor2;                                                                      //   (4) 	Mult = Factor1 * Factor2
        return Mult;
    }
}

