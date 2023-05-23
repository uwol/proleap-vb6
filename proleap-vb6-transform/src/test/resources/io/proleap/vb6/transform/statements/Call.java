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


public class Call {
    static {
        SubExample1();                                                                               //   (2) Call SubExample1
        SubExample1();                                                                               //   (3) SubExample1
        SubExample2("some arg");                                                                     //   (5) Call SubExample2("some arg")
        SubExample2("some arg");                                                                     //   (6) SubExample2 "some arg"
                                                                                                     //   (8) ' function calls
        FunctionExample1();                                                                          //   (9) Call FunctionExample1
        FunctionExample1();                                                                          //  (10) FunctionExample1
        FunctionExample2(new Double(2.0), 3, 4, 2);                                                  //  (12) Call FunctionExample2(2.0, 3, 4, 2)
        FunctionExample2(new Double(2.0), 3, 4, 2);                                                  //  (13) FunctionExample2 2.0, 3, 4, 2
    }
    
    protected static void SubExample1(){                                                             //  (15) Sub SubExample1()
    }
    
                                                                                                     //  (16) 	' sub body
                                                                                                     //  (17) End Sub
    protected static void SubExample2(String Arg1){                                                  //  (19) Sub SubExample2(Arg1)
    }
    
                                                                                                     //  (20) 	' sub body
                                                                                                     //  (21) End Sub
    protected static Object FunctionExample1(){                                                      //  (23) Function FunctionExample1()
        Object FunctionExample1 = null;
        return FunctionExample1;
    }
    
                                                                                                     //  (24) 	' function body
                                                                                                     //  (25) End Function
    protected static Object FunctionExample2(Double FirstArg, Integer AdditionalArgs){               //  (27) Function FunctionExample2(ByVal FirstArg As Double, ParamArray AdditionalArgs())
        Object FunctionExample2 = null;
        return FunctionExample2;
    }
}

