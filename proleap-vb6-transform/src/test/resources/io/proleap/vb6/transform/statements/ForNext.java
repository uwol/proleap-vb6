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


public class Fornext {
    static {
        Integer I = null;                                                                            //   (1) Dim I As Integer, J As Integer, K As Variant
        Integer J = null;                                                                           
        Object K = null;                                                                            
        final Integer L = 10;                                                                        //   (3) Private Const L As Integer = 10
        
        for(I = 1; I <= 10; I++){                                                                    //   (6) For I = 1 TO 10
            Beep();                                                                                  //   (7) 	Beep
        }
                                                                                                     //   (8) Next
        for(J = 0; J <= I; J++){                                                                     //  (11) For J=0 TO I
            Beep();                                                                                  //  (12) 	Beep
        }
                                                                                                     //  (13) Next J
        for(I = 0; I <= toInt(K.Value); I++){                                                        //  (16) For I=0 To K.Value
            for(J = 1; J <= 20; J++){                                                                //  (17) 	For J=1 To 20 Step 2
                Beep();                                                                              //  (18) 		Beep
            }
        }
                                                                                                     //  (19) 	Next
                                                                                                     //  (20) Next
        for(I = 1; I <= 32000; I++){                                                                 //  (23) For i = 1 To 32000: Next i
        }
    }
}

