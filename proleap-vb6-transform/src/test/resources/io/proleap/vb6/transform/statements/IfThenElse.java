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


public class Ifthenelse {
    static {
        Boolean IsVisible = null;                                                                    //   (1) Dim IsVisible As Boolean
        IsVisible=true;                                                                              //   (2) IsVisible = True
        if(IsVisible){                                                                               //   (4) If IsVisible Then 
            Beep();                                                                                  //   (5) 	Beep
            IsVisible=false;                                                                         //   (6) 	IsVisible = false
        }
        else{                                                                                        //   (7) Else
            Beep();                                                                                  //   (8) 	Beep
            Beep();                                                                                  //   (9) 	Beep
        }
        
    }
}

