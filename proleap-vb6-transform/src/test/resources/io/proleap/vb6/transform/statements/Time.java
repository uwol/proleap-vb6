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


public class Time {
    static {
        java.util.Date SomeTime = null;                                                              //   (1) Dim SomeTime
        SomeTime=new java.util.Date("#8:00:00 AM#");                                                 //   (2) SomeTime = #8:00:00 AM#
        new Date()=SomeTime;                                                                         //   (3) Time = SomeTime 
    }
}

