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


public class Type {
    
    private class Typename1{                                                                         //   (1) Private Type TypeName1
        Integer Variable1 = null;                                                                    //   (2) 	Variable1 As Integer
        
        Double Variable2 = null;                                                                     //   (3) 	Variable2 As Double
        
    }
    
    
                                                                                                     //   (4) End Type
    public class Typename2{                                                                          //   (6) Public Type TypeName2
        String Variable1 = null;                                                                     //   (7) 	Variable1 As Currency
        
                                                                                                     //   (8) 	
        String Variable2 = null;                                                                     //   (9) 	Variable2 As String
        
        Object Variable3 = null;                                                                     //  (10) 	Variable3
        
        Typename1 Variable4 = null;                                                                  //  (11) 	Variable4 As TypeName1
        
    }
    
    static {
                                                                                                     //  (12) End Type
        Typename1 typeElement = null;                                                                //  (15) Dim typeElement As TypeName1
        typeElement.Variable1=1;                                                                     //  (16) typeElement.Variable1 =1
        Typename2 typeElement2 = null;                                                               //  (18) Dim typeElement2 As TypeName2
        typeElement2.Variable2="test";                                                               //  (19) typeElement2.Variable2 = "test"
        VbStaticArray<Typename2> typeArray = new VbStaticArray<Typename2>(10);                       //  (22) Dim typeArray(1 To 10) As TypeName2
        typeArray.getElement(1).Variable2="hello world";                                             //  (23) typeArray(1).Variable2 = "hello world"
    }
}

