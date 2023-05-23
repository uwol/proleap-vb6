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


public class Enum {
    public enum Enum1 implements VbValueEnum{                                                        //   (3) Public Enum Enum1
        EnumConst1(1), EnumConst2(2), EnumConst3(-1), EnumConst4(new VbColor("&H123ABC&"));
        
        private long position;
        
        private Enum1(final long position){
            this.position = position;
        }
        
        public long getPosition(){
            return this.position;
        }
    }
    
                                                                                                     //   (4) 	EnumConst1=1
                                                                                                     //   (5) 	EnumConst2 = 2
                                                                                                     //   (6) 	EnumConst3 = -1
                                                                                                     //   (7) 	EnumConst4 = &H123ABC&
                                                                                                     //   (8) End Enum
    public enum Enum2 implements VbValueEnum{                                                        //  (11) Private Enum Enum2
        EC1(0), EC2(1), EC3(2);
        
        private long position;
        
        private Enum2(final long position){
            this.position = position;
        }
        
        public long getPosition(){
            return this.position;
        }
    }
    
                                                                                                     //  (12) 	EC1
                                                                                                     //  (13) 	EC2
                                                                                                     //  (14) 	EC3
                                                                                                     //  (15) End Enum
    public enum Days implements VbValueEnum{                                                         //  (17) Public Enum Days
        Monday(0), Tuesday(1), Wednesday(2), Thursday(3), Friday(4), Saturday(5), Sunday(6);
        
        private long position;
        
        private Days(final long position){
            this.position = position;
        }
        
        public long getPosition(){
            return this.position;
        }
    }
    
}

