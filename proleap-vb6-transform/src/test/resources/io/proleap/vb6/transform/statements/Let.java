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


public class Let {
    static {
        String Var1 = null;                                                                          //   (1) Dim Var1, Var2
        Integer Var2 = null;                                                                        
        Var1="test";                                                                                 //   (3) Let Var1 = "test"
        Var2=2;                                                                                      //   (4) Let Var2 = 2
        Var1="hello world";                                                                          //   (5) Var1 = "hello world"
        Var1="hello world";                                                                          //   (6) Var1= "hello world"
        Var1="hello world";                                                                          //   (7) Var1 ="hello world"
        Var1="hello world";                                                                          //   (8) Var1="hello world"
    }
    
    protected static Object SomeFunction(){                                                          //  (10) Function SomeFunction()
        Object SomeFunction = null;
        Var2=4;                                                                                      //  (11) 	Var2 = 4
        Var2=6;                                                                                      //  (12) 	Let Var2 = 6
        return SomeFunction;
    }
}

