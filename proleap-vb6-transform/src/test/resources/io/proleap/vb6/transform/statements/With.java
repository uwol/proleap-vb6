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


public class With {
    static {
        Object Object = null;                                                                        //   (1) Dim Object As Variant
        {                                                                                            //   (3) With Object
            Object withContextVar = Object;
            withContextVar.MemberPropertyCall="SomeValue";                                           //   (4) 	.MemberPropertyCall = "SomeValue"
            withContextVar.MemberFunctionCall();                                                     //   (5) 	.MemberFunctionCall
        }
    }
}

