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


public class Propertyset {
    static {
        Integer CurrentI = null;                                                                     //   (1) Dim CurrentI As Integer
        Object CurrentObj = null;                                                                    //   (2) Dim CurrentObj As Object
    }
    
    protected static void CurrentI(Integer I){                                                       //   (4) Property Set CurrentI(I As Integer)
        CurrentI=I;                                                                                  //   (5) 	 CurrentI = I 
    }
    
                                                                                                     //   (6) End Property
    protected static void CurrentObject(Object J){                                                   //   (8) Property Set CurrentObject(J AS Object)
        CurrentObj=J;                                                                                //   (9) 	Set CurrentObj = J 
    }
}

