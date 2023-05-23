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


public class Whilewend {
    static {
        Integer I = null;                                                                            //   (1) Dim I, J
        Integer J = null;                                                                           
        I=20;                                                                                        //   (2) I = 20
        J=30;                                                                                        //   (3) J = 30
        while(I > 0){                                                                                //   (5) While I > 0
            I=I - 1;                                                                                 //   (6) 	I = I - 1
        }
                                                                                                     //   (7) Wend
        I=100;                                                                                       //  (10) I = 100
        while(I > 0){                                                                                //  (12) While I > 0
            while(I % 15 != 0){                                                                      //  (13) 	While I Mod 15 <> 0
                I=I - 1;                                                                             //  (14) 		I = I - 1
            }
        }
    }
}

