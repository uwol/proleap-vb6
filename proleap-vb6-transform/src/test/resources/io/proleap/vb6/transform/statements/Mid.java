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


public class Mid {
    static {
        String SomeString = null;                                                                    //   (1) Dim SomeString
        SomeString="Hello world";                                                                    //   (2) SomeString = "Hello world"
        Mid(SomeString, 0, 1)="L";                                                                   //   (3) Mid(SomeString, 0, 1) = "L"
        Mid(SomeString, 5)="   ";                                                                    //   (4) Mid(SomeString, 5) = "   "
    }
}

