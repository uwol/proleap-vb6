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


public class Sub {
    
    protected static void Mult(Integer Factor1, Integer Factor2){                                    //   (2) Sub Mult(ByVal Factor1 As Integer, Factor2 As Integer)
        Integer Product = null;                                                                      //   (3) 	Dim Product
        Product=Factor1 * Factor2;                                                                   //   (4) 	Product = Factor1 * Factor2
                                                                                                     //   (5) 	' some comment
        Debug.Print(Product);                                                                        //   (6) 	Debug.Print Product
    }
}

