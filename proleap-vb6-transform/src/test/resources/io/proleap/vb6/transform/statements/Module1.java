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


public class Module1 {
    Object SomeVariable = null;                                                                      //  (14) Public SomeVariable
    Module1 SomeModule = null;                                                                       //  (15) Public SomeModule As New Module1
    
    protected Object Function1(){                                                                    //  (17) Function Function1()
        Object Function1 = null;
        return Function1;
    }
    
                                                                                                     //  (19) End Function
    protected Object Function2(Object I, Object J){                                                  //  (21) Function Function2(I, J)
        Object Function2 = null;
        return Function2;
    }
    
                                                                                                     //  (23) End Function
    protected void Sub1(){                                                                           //  (25) Sub Sub1()
    }
    
                                                                                                     //  (27) End Sub
    protected void Sub2(Object I, Object J){                                                         //  (29) Sub Sub2(I, J)
    }
    
                                                                                                     //  (31) End Sub
    protected Module1 GetModule(){                                                                   //  (33) Function GetModule() As Module1
        Module1 GetModule = null;
        Module1 Module = null;                                                                       //  (34)     Dim Module As New Module1
        GetModule=Module;                                                                            //  (35)     Set GetModule = Module
        return GetModule;
    }
}

