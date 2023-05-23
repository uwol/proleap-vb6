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


public class Goto {
    
    protected static Object SomeFunction(){                                                          //   (1) Function SomeFunction()
        Object SomeFunction = null;
        String Text = null;                                                                          //   (2) 	Dim Text As String
        Text="Hello World";                                                                          //   (3) 	Text = "Hello World"
                                                                                                     //   (5) 	Goto LineLabel1
                                                                                                     //   (6) 	
                                                                                                     //   (7) LineLabel1:
        Text="1";                                                                                    //   (8) 	Text = "1"
                                                                                                     //   (9) LineLabel2:
        Text="2";                                                                                    //  (10) 	Text = "2"
        Debug.Print(Text);                                                                           //  (11) 	Debug.Print Text 
        return SomeFunction;
    }
    static {
                                                                                                     //  (12) End Function
        SomeFunction();                                                                              //  (15) SomeFunction
    }
}

