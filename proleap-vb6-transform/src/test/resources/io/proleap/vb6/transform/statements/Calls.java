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


public class Calls {
    
    private static Integer Function1(){                                                              //   (1) Private Function Function1()
        Integer Function1 = null;
        Function1=1;                                                                                 //   (2)     Function1 = 1
        return Function1;
    }
    
                                                                                                     //   (3) End Function
    private static Integer Function2(Integer I, Integer J){                                          //   (5) Private Function Function2(I, J)
        Integer Function2 = null;
        Function2=2;                                                                                 //   (6)     Function2 = 2
        return Function2;
    }
    
                                                                                                     //   (7) End Function
    private static Integer Sub1(){                                                                   //   (9) Private Function Sub1()
        Integer Sub1 = null;
        Sub1=3;                                                                                      //  (10)     Sub1 = 3
        return Sub1;
    }
    
                                                                                                     //  (11) End Function
    private static Integer Sub2(Integer I, Integer J){                                               //  (13) Private Function Sub2(I, J)
        Integer Sub2 = null;
        Sub2=4;                                                                                      //  (14)     Sub2 = 4
        return Sub2;
    }
    
                                                                                                     //  (15) End Function
    private static void TestSimpleCalls(){                                                           //  (17) Private Sub TestSimpleCalls()
        VbStaticArray<Object> SomeVariable = new VbStaticArray<Object>(1);                           //  (18)     Dim SomeVariable(1)
                                                                                                     //  (20)     ''
                                                                                                     //  (21)     '' explicit call statement in block
                                                                                                     //  (22)     ''
                                                                                                     //  (23)     
                                                                                                     //  (24)     '' Call SomeVariable
        Function1();                                                                                 //  (26)     Call Function1
                                                                                                     //  (27)     '' Call Function1 ()
                                                                                                     //  (28)     
                                                                                                     //  (29)     '' Call Function2 1, 2
        Function2(1, 2);                                                                             //  (30)     Call Function2(1, 2)
                                                                                                     //  (31)     
        Sub1();                                                                                      //  (32)     Call Sub1
                                                                                                     //  (33)     '' Call Sub1 ()
                                                                                                     //  (34)     
                                                                                                     //  (35)     '' Call Sub2 1, 2
        Sub2(1, 2);                                                                                  //  (36)     Call Sub2(1, 2)
                                                                                                     //  (40)     ''
                                                                                                     //  (41)     '' implicit call statement in block
                                                                                                     //  (42)     ''
                                                                                                     //  (44)     '' SomeVariable
        Function1();                                                                                 //  (46)     Function1
                                                                                                     //  (47)     '' Function1()
                                                                                                     //  (48)     
        Function2(1, 2);                                                                             //  (49)     Function2 1, 2
                                                                                                     //  (50)     '' Function2 (1, 2)
                                                                                                     //  (51)     
        Sub1();                                                                                      //  (52)     Sub1
                                                                                                     //  (53)     '' Sub1()
                                                                                                     //  (54)     
        Sub2(1, 2);                                                                                  //  (55)     Sub2 1, 2
                                                                                                     //  (56)     '' Sub2 (1, 2)
                                                                                                     //  (59)     ''
                                                                                                     //  (60)     '' implicit call statement in statement
                                                                                                     //  (61)     ''
        Integer I = null;                                                                            //  (62)     Dim I
                                                                                                     //  (63)     
        I=(Integer) SomeVariable;                                                                    //  (64)     I = SomeVariable
                                                                                                     //  (65)     '' I = SomeVariable 0
        I=(Integer) SomeVariable.getElement(0);                                                      //  (66)     I = SomeVariable(0) '' array element call
                                                                                                     //  (67)     
        I=Function1();                                                                               //  (68)     I = Function1
        I=Function1();                                                                               //  (69)     I = Function1()
                                                                                                     //  (70)     '' I = Call Function1
                                                                                                     //  (71)     '' I = Call Function1()
                                                                                                     //  (72)     
                                                                                                     //  (73)     '' I = Function2 1, 2
        I=Function2(1, 2);                                                                           //  (74)     I = Function2(1, 2)
                                                                                                     //  (75)     
        I=Sub1();                                                                                    //  (76)     I = Sub1
        I=Sub1();                                                                                    //  (77)     I = Sub1()
                                                                                                     //  (78)     '' I = Call Sub1()
                                                                                                     //  (79)     
                                                                                                     //  (80)     '' I = Sub2 1, 2
        I=Sub2(1, 2);                                                                                //  (81)     I = Sub2(1, 2)
    }
    
                                                                                                     //  (82)     '' I = Call Sub2(1, 2)
                                                                                                     //  (83) End Sub
    private static void TestMemberCalls(Module1 Module){                                             //  (85) Private Sub TestMemberCalls(Module As Module1)
                                                                                                     //  (86)     ''
                                                                                                     //  (87)     '' explicit call statement in block
                                                                                                     //  (88)     ''
                                                                                                     //  (89)     
                                                                                                     //  (90)     '' Call Module.SomeVariable
                                                                                                     //  (91)     '' Call Module.SomeVariable(1)
        Module.Function1();                                                                          //  (93)     Call Module.Function1
                                                                                                     //  (94)     '' Call Module.Function1 ()
                                                                                                     //  (95)     
                                                                                                     //  (96)     '' Call Module.Function2 1, 2
        Module.Function2(1, 2);                                                                      //  (97)     Call Module.Function2(1, 2)
                                                                                                     //  (98)     
        Module.Sub1();                                                                               //  (99)     Call Module.Sub1
                                                                                                     // (100)     '' Call Module.Sub1 ()
                                                                                                     // (101)     
                                                                                                     // (102)     '' Call Module.Sub2 1, 2
        Module.Sub2(1, 2);                                                                           // (103)     Call Module.Sub2(1, 2)
                                                                                                     // (107)     ''
                                                                                                     // (108)     '' implicit call statement in block
                                                                                                     // (109)     ''
                                                                                                     // (111)     '' Module.SomeVariable
        Module.Function1();                                                                          // (113)     Module.Function1
                                                                                                     // (114)     '' Module.Function1()
                                                                                                     // (115)     
        Module.Function2(1, 2);                                                                      // (116)     Module.Function2 1, 2
                                                                                                     // (117)     '' Module.Function2 (1, 2)
                                                                                                     // (118)     
        Module.Sub1();                                                                               // (119)     Module.Sub1
                                                                                                     // (120)     '' Module.Sub1()
                                                                                                     // (121)     
        Module.Sub2(1, 2);                                                                           // (122)     Module.Sub2 1, 2
                                                                                                     // (123)     '' Module.Sub2 (1, 2)
                                                                                                     // (126)     ''
                                                                                                     // (127)     '' implicit call statement in statement
                                                                                                     // (128)     ''
        Object I = null;                                                                             // (129)     Dim I
                                                                                                     // (130)     
        I=Module.SomeVariable;                                                                       // (131)     I = Module.SomeVariable
                                                                                                     // (132)     
        I=Module.Function1;                                                                          // (133)     I = Module.Function1
        I=Module.Function1();                                                                        // (134)     I = Module.Function1()
                                                                                                     // (135)     '' I = Call Module.Function1
                                                                                                     // (136)     '' I = Call Module.Function1()
                                                                                                     // (137)     
                                                                                                     // (138)     '' I = Module.Function2 1, 2
        I=Module.Function2(1, 2);                                                                    // (139)     I = Module.Function2(1, 2)
    }
    
                                                                                                     // (140)     
                                                                                                     // (141)     '' I = Module.Sub1 '' function or variable expected
                                                                                                     // (142)     '' I = Module.Sub1() '' function or variable expected
                                                                                                     // (143)     '' I = Call Module.Sub1() '' function or variable expected
                                                                                                     // (144)     
                                                                                                     // (145)     '' I = Module.Sub2 1, 2 '' function or variable expected
                                                                                                     // (146)     '' I = Module.Sub2(1, 2) '' function or variable expected
                                                                                                     // (147)     '' I = Call Module.Sub2(1, 2) '' function or variable expected
                                                                                                     // (148) End Sub
    private static void TestMemberCallsRecursive(Module1 Module){                                    // (150) Private Sub TestMemberCallsRecursive(Module As Module1)
                                                                                                     // (151)     ''
                                                                                                     // (152)     '' explicit call statement in block
                                                                                                     // (153)     ''
                                                                                                     // (154)     
                                                                                                     // (155)     '' Call Module.SomeModule.SomeVariable
                                                                                                     // (156)     '' Call Module.SomeModule().SomeVariable
                                                                                                     // (157)     '' Call Module.GetModule.SomeVariable
                                                                                                     // (158)     '' Call Module.GetModule().SomeVariable
        Module.SomeModule.Function1();                                                               // (161)     Call Module.SomeModule.Function1
        Module.SomeModule().Function1();                                                             // (162)     Call Module.SomeModule().Function1
        Module.GetModule.Function1();                                                                // (163)     Call Module.GetModule.Function1
        Module.GetModule().Function1();                                                              // (164)     Call Module.GetModule().Function1
        Module.GetModule().GetModule().Function1();                                                  // (165)     Call Module.GetModule().GetModule().Function1
                                                                                                     // (166)     '' Call Module.GetModule.Function1 ()
                                                                                                     // (167)     '' Call Module.SomeModule.Function1 ()
                                                                                                     // (168)     
                                                                                                     // (169)     '' Call Module.GetModule.Function2 1, 2
                                                                                                     // (170)     '' Call Module.SomeModule.Function2 1, 2
        Module.GetModule.Function2(1, 2);                                                            // (171)     Call Module.GetModule.Function2(1, 2)
        Module.SomeModule.Function2(1, 2);                                                           // (172)     Call Module.SomeModule.Function2(1, 2)
        Module.GetModule().Function2(1, 2);                                                          // (173)     Call Module.GetModule().Function2(1, 2)
        Module.SomeModule().Function2(1, 2);                                                         // (174)     Call Module.SomeModule().Function2(1, 2)
                                                                                                     // (175)     
        Module.GetModule.Sub1();                                                                     // (176)     Call Module.GetModule.Sub1
        Module.GetModule().Sub1();                                                                   // (177)     Call Module.GetModule().Sub1
                                                                                                     // (178)     '' Call Module.GetModule.Sub1 ()
                                                                                                     // (179)     
                                                                                                     // (180)     '' Call Module.GetModule.Sub2 1, 2
        Module.GetModule.Sub2(1, 2);                                                                 // (181)     Call Module.GetModule.Sub2(1, 2)
        Module.GetModule().Sub2(1, 2);                                                               // (182)     Call Module.GetModule().Sub2(1, 2)
                                                                                                     // (186)     ''
                                                                                                     // (187)     '' implicit call statement in block
                                                                                                     // (188)     ''
                                                                                                     // (190)     '' Module.SomeModule.SomeVariable
        Module.GetModule().Function1();                                                              // (192)     Module.GetModule().Function1
                                                                                                     // (193)     '' Module.GetModule().Function1()
                                                                                                     // (194)     
        Module.GetModule().Function2(1, 2);                                                          // (195)     Module.GetModule().Function2 1, 2
                                                                                                     // (196)     '' Module.GetModule().Function2 (1, 2)
                                                                                                     // (197)     
        Module.GetModule().Sub1();                                                                   // (198)     Module.GetModule().Sub1
                                                                                                     // (199)     '' Module.GetModule().Sub1()
                                                                                                     // (200)     
        Module.GetModule().Sub2(1, 2);                                                               // (201)     Module.GetModule().Sub2 1, 2
                                                                                                     // (202)     '' Module.GetModule().Sub2 (1, 2)
                                                                                                     // (205)     ''
                                                                                                     // (206)     '' implicit call statement in statement
                                                                                                     // (207)     ''
        Object I = null;                                                                             // (208)     Dim I
                                                                                                     // (209)     
        I=Module.GetModule().SomeVariable;                                                           // (210)     I = Module.GetModule().SomeVariable
                                                                                                     // (211)     
        I=Module.GetModule().Function1;                                                              // (212)     I = Module.GetModule().Function1
        I=Module.GetModule().Function1();                                                            // (213)     I = Module.GetModule().Function1()
                                                                                                     // (214)     '' I = Call Module.GetModule().Function1
                                                                                                     // (215)     '' I = Call Module.GetModule().Function1()
                                                                                                     // (216)     
                                                                                                     // (217)     '' I = Module.GetModule().Function2 1, 2
        I=Module.GetModule().Function2(1, 2);                                                        // (218)     I = Module.GetModule().Function2(1, 2)
    }
    
                                                                                                     // (219)     
                                                                                                     // (220)     '' I = Module.GetModule().Sub1 '' function or variable expected
                                                                                                     // (221)     '' I = Module.GetModule().Sub1() '' function or variable expected
                                                                                                     // (222)     '' I = Call Module.GetModule().Sub1() '' function or variable expected
                                                                                                     // (223)     
                                                                                                     // (224)     '' I = Module.GetModule().Sub2 1, 2 '' function or variable expected
                                                                                                     // (225)     '' I = Module.GetModule().Sub2(1, 2) '' function or variable expected
                                                                                                     // (226)     '' I = Call Module.GetModule().Sub2(1, 2) '' function or variable expected
                                                                                                     // (227) End Sub
    private static void Main(){                                                                      // (230) Private Sub Main()
        Module1 Module = null;                                                                       // (231)     Dim Module As New Module1
                                                                                                     // (232)     
        TestSimpleCalls();                                                                           // (233)     TestSimpleCalls
        TestMemberCalls(Module);                                                                     // (234)     TestMemberCalls Module
        TestMemberCallsRecursive(Module);                                                            // (235)     TestMemberCallsRecursive Module
    }
}

