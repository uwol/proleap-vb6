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


public class Example {
    
    protected static Integer Mult(Integer Factor1, Integer Factor2){                                 //   (1) Function Mult(ByVal Factor1 As Integer, Factor2 As Integer)
        Integer Mult = null;
        Mult=Factor1 * Factor2;                                                                      //   (2)     Mult = Factor1 * Factor2
        return Mult;
    }
    static {
                                                                                                     //   (3) End Function
        Integer VariantProduct = null;                                                               //   (5) Dim VariantProduct
        VariantProduct=Mult(2, 3);                                                                   //   (6) VariantProduct = Mult(2, 3)
        Integer I = null;                                                                            //   (8) Dim I
        for(I = 1; I <= VariantProduct; I++){                                                        //   (9) For I = 1 TO VariantProduct
            Beep();                                                                                  //  (10)     Beep
        }
    }
}

