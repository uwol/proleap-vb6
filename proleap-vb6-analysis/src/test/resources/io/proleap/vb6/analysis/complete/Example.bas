Function Mult(ByVal Factor1 As Integer, Factor2 As Integer)
    Mult = Factor1 * Factor2
End Function

Dim VariantProduct
VariantProduct = Mult(2, 3)

Dim I
For I = 1 TO VariantProduct
    Beep
Next
