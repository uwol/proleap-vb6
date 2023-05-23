package io.proleap.vb6.transform.java.type;

import java.util.HashMap;
import java.util.Map;

import io.proleap.vb6.asg.metamodel.type.VbBaseType;

public class TypeMapping {

	public final static Map<VbBaseType, JavaType> typeMapping = new HashMap<VbBaseType, JavaType>();

	static {
		typeMapping.put(VbBaseType.BOOLEAN, JavaType.BOOLEAN);
		typeMapping.put(VbBaseType.BYTE, JavaType.BYTE);
		typeMapping.put(VbBaseType.COLLECTION, JavaType.COLLECTION);
		typeMapping.put(VbBaseType.COLOR, JavaType.COLOR);
		typeMapping.put(VbBaseType.CURRENCY, JavaType.STRING);
		typeMapping.put(VbBaseType.DATE, JavaType.DATE);
		typeMapping.put(VbBaseType.DOUBLE, JavaType.DOUBLE);
		typeMapping.put(VbBaseType.FILENUMBER, JavaType.VB_FILE_NUMBER);
		typeMapping.put(VbBaseType.INTEGER, JavaType.INTEGER);
		typeMapping.put(VbBaseType.LONG, JavaType.LONG);
		typeMapping.put(VbBaseType.SINGLE, JavaType.DOUBLE);
		typeMapping.put(VbBaseType.STRING, JavaType.STRING);
		typeMapping.put(VbBaseType.VARIANT, JavaType.OBJECT);
	}
}
