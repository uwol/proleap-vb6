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


public class Date {
    static {
        java.util.Date DateVar = null;                                                               //   (1) Dim DateVar
        java.util.Date DateVar2 = null;                                                              //   (2) Dim DateVar2
        DateVar=new java.util.Date("#December, 24, 2000#");                                          //   (4) DateVar = #December, 24, 2000#
        DateVar2=DateVar;                                                                            //   (5) DateVar2 = DateVar
        setDate(DateVar2);                                                                           //   (6) Date = DateVar2 ' Set system date
        Object DateVar3 = null;                                                                      //   (8) Dim DateVar3 As Variant, DateVar4 As String
        String DateVar4 = null;                                                                     
        DateVar3=new Date();                                                                         //  (10) DateVar3 = Date
        DateVar4=String.valueOf(new Date());                                                         //  (11) DateVar4 = Date$
    }
}

