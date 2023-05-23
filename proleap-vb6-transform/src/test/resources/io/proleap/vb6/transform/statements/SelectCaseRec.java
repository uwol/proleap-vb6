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


public class Selectcaserec {
    static {
        String CondA = null;                                                                         //   (1) Dim CondA As String
        String CondB = null;                                                                         //   (2) Dim CondB As String
        String selectVariable0 = CondA;                                                              //   (4) Select Case CondA
        if(selectVariable0.equals("A")){
                                                                                                     //   (5) 	Case "A"
            Beep();                                                                                  //   (6) 		Beep
        }
        else if(selectVariable0.equals("B")){
                                                                                                     //   (7) 	Case "B"
            String selectVariable1 = CondB;                                                          //   (8) 		Select Case CondB
            if(selectVariable1.equals("A")){
                                                                                                     //   (9) 			Case "A"
                Beep();                                                                              //  (10) 				Beep
            }
            else if(selectVariable1.equals("B")){
                                                                                                     //  (11) 			Case "B"
                Beep();                                                                              //  (12) 				Beep
            }
            else {
                                                                                                     //  (13) 			Case Else
                Beep();                                                                              //  (14) 				Beep
            }
        }
        else {
                                                                                                     //  (15) 		End Select
                                                                                                     //  (16) 	Case Else
            Beep();                                                                                  //  (17) 		Beep
        }
    }
}

