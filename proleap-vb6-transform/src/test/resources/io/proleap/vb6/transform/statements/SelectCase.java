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


public class Selectcase {
    static {
        String Grade = null;                                                                         //   (1) Dim Grade As String
        Grade="B";                                                                                   //   (2) Grade = "B"
        String selectVariable0 = Grade;                                                              //   (4) Select Case Grade
        if(selectVariable0.equals("A")){
                                                                                                     //   (5) 	Case "A"
            Beep();                                                                                  //   (6) 		Beep
        }
        else if(selectVariable0.equals("B")){
                                                                                                     //   (7) 	Case "B"
            Beep();                                                                                  //   (8) 		Beep
        }
        else {
                                                                                                     //   (9) 	Case Else
            Beep();                                                                                  //  (10) 		Beep
        }
                                                                                                     //  (11) End Select
        Integer Percent = null;                                                                      //  (13) Dim Percent
        Percent=50;                                                                                  //  (14) Percent = 50
        switch(Percent){                                                                             //  (16) Select Case Percent
            case 0:                                                                                  //  (17) 	Case 0 To 25
            case 25:                                                                                
            Beep();                                                                                  //  (18) 		Beep
            break;                                                                                  
            case 26:                                                                                 //  (19) 	Case 26 To 49, 50
            case 49:                                                                                
            case 50:                                                                                
            break;                                                                                  
            case 51:                                                                                 //  (20) 	Case 51 To 75
            case 75:                                                                                
            Beep();                                                                                  //  (21) 		Beep
            break;                                                                                  
            case 76:                                                                                 //  (22) 	Case 76 To 100
            case 100:                                                                               
            break;                                                                                  
            default:                                                                                 //  (23) 	Case Else
            Beep();                                                                                  //  (24) 		Beep
            break;                                                                                  
        }
                                                                                                     //  (25) End Select
        String selectVariable0 = Grade;                                                              //  (27) Select Case Grade
        if(selectVariable0.equals("A")){
                                                                                                     //  (28) 	Case "A"
            Beep();                                                                                  //  (29) 		Beep
        }
        else {
                                                                                                     //  (30) 	Case Else
            Beep();                                                                                  //  (31) 		Beep
        }
        else if(selectVariable0.equals("B")){
                                                                                                     //  (32) 	Case "B"
            Beep();                                                                                  //  (33) 		Beep
        }
        else {
                                                                                                     //  (34) 	Case Else
            Beep();                                                                                  //  (35) 		Beep
        }
    }
}

