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


public class Doloop {
    static {
        Integer I = null;                                                                            //   (1) Dim I
        I=5;                                                                                         //   (2) I = 5
        
        while(I != 0){                                                                               //   (4) Do While Not I = 0
            Beep();                                                                                  //   (5) 	Beep
            I=1;                                                                                     //   (6) 	I -= 1
        }
        
                                                                                                     //   (7) Loop
        while(I == 0){                                                                               //  (10) Do Until I=0
            Beep();                                                                                  //  (11) 	Beep
            I=1;                                                                                     //  (12) 	I -= 1
        }
    }
}

