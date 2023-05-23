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


public class Const {
    static {
        final String Var1 = "test";                                                                  //   (1) Const Var1 = "test"
        
        final Integer Var2 = 345;                                                                    //   (2) Private Const Var2 = 345
        
        final Double Var3 = new Double(567.1);                                                       //   (3) Public Const Var3 As Double = 567.1 
        
        final String Var4 = "test2";                                                                 //   (5) Const Var4 ="test2", Var5 As Integer =3 , Var6 as String = "test3" 
        
        final Integer Var5 = 3;                                                                     
        
        final String Var6 = "test3";                                                                
        
                                                                                                     //   (6) ' some comment
        final String Var7 = "Hello \"quoted\" world";                                                //   (7) Const Var7 ="Hello ""quoted"" world"
        
    }
    
    private static void Test(){                                                                      //  (10) Private Sub Test()
        final Double PI = new Double(3.1415);                                                        //  (11) 	Const PI As Double = 3.1415 ' define pi
        
    }
}

