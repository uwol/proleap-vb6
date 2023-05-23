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


public class Write {
    static {
        Open("c:/test.csv", new VbFileNumber("#I"));                                                 //   (1) Open "c:/test.csv" For Output As #I
        Write(new VbFileNumber("#I"), );                                                             //   (3) Write #I, 'blank
        Write(new VbFileNumber("#I"), "Cell 1", 123, "Cell 3", "Cell 4");                            //   (4) Write #I, "Cell 1", 123, "Cell 3", "Cell 4"
        Write(new VbFileNumber("#I"), 123, 456);                                                     //   (5) Write #I, 123; 456
        Object MovieTitle = null;                                                                    //   (7) Dim MovieTitle
        Object Ean = null;                                                                           //   (8) Dim Ean
        Object MovieSubTitle = null;                                                                 //   (9) Dim MovieSubTitle
        Object Date = null;                                                                          //  (10) Dim Date
        Object Price = null;                                                                         //  (11) Dim Price
        print(new VbFileNumber("#I"), MovieTitle, 20, Format((Object) Ean, "@@@@"), 40, MovieSubTitle, 60, Format((Object) new Date(), "m/d/yyyy"), 80, Format(Format((Object) Price, "#0.00"), "@@@@@")); //  (13) Print #I, MovieTitle; Tab(20); Format$(Ean, "@@@@"); _
                                                                                                     //  (14) 	Tab(40); MovieSubTitle; _
                                                                                                     //  (15) 	Tab(60); Format$(Date, "m/d/yyyy"); _
                                                                                                     //  (16) 	Tab(80); Format$(Format$(Price, "#0.00"), "@@@@@")
        Close();                                                                                     //  (18) Close
    }
}

