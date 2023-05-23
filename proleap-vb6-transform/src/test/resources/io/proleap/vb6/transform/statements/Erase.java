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


public class Erase {
    static {
        VbStaticArray<Integer> StaticArray = new VbStaticArray<Integer>(10);                         //   (1) Dim StaticArray(10) AS Integer
        VbDynamicArray<Integer> DynamicArray = new VbDynamicArray<Integer>();                        //   (2) Dim DynamicArray() AS Integer
    }
}

