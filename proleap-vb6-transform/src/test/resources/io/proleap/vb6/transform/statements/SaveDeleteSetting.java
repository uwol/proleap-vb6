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


public class Savedeletesetting {
    static {
        String StringVar = null;                                                                     //   (3) Dim StringVar As String
        final String StringConst = "Section2";                                                       //   (4) Const StringConst As String = "Section2"
        
        StringVar="Section3";                                                                        //   (5) StringVar = "Section3"
        SaveSetting("AppName", "Section", "Key", 20);                                                //   (7) SaveSetting "AppName", "Section", "Key", 20
        SaveSetting("AppName", "Section", "Key2", 40);                                               //   (8) SaveSetting "AppName" , "Section" ,"Key2", 40
        SaveSetting("AppName", "Section", "Key2", 40);                                               //   (9) SaveSetting "AppName" , "Section" ,"Key2", 40
        SaveSetting("AppName", StringConst, "Key3", 50);                                             //  (10) SaveSetting "AppName" , StringConst ,"Key3", 50
        SaveSetting("AppName", StringVar, "Key4", StringConst);                                      //  (11) SaveSetting "AppName" , StringVar ,"Key4", StringConst
        DeleteSetting("AppName", "Section", "Key");                                                  //  (14) DeleteSetting "AppName", "Section", "Key"
        DeleteSetting("AppName", "Section");                                                         //  (15) DeleteSetting "AppName", "Section"
        DeleteSetting("AppName", StringVar, "Key3");                                                 //  (16) DeleteSetting "AppName", StringVar, "Key3"
    }
}

