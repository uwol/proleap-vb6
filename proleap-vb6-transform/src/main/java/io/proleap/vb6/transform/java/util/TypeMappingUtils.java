package io.proleap.vb6.transform.java.util;

import io.proleap.vb6.asg.metamodel.type.BaseType;
import io.proleap.vb6.asg.metamodel.type.ComplexType;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.transform.java.type.JavaType;
import io.proleap.vb6.transform.java.type.TypeMapping;

public class TypeMappingUtils {

	public static String mapType(final BaseType type) {
		final String result;

		if (type == null) {
			result = TypeMapping.typeMapping.get(VbBaseType.VARIANT).getName();
		} else {
			final BaseType baseType = type;
			final String baseTypeName = baseType.getName();
			final VbBaseType vbType = VbBaseType.forString(baseTypeName);
			final JavaType primitiveType = TypeMapping.typeMapping.get(vbType);

			result = primitiveType.getName();
		}

		return result;
	}

	public static String mapType(final ComplexType type) {
		final String result;

		if (type == null) {
			result = TypeMapping.typeMapping.get(VbBaseType.VARIANT).getName();
		} else {
			final ComplexType complexType = type;
			final String complexTypeName = complexType.getName();
			final String className = ClassUtils.getClassName(complexTypeName);

			result = className;
		}

		return result;
	}

	public static String mapType(final Type type) {
		final String result;

		if (type == null) {
			result = TypeMapping.typeMapping.get(VbBaseType.VARIANT).getName();
		} else if (type instanceof BaseType) {
			result = mapType((BaseType) type);
		} else if (type instanceof ComplexType) {
			result = mapType((ComplexType) type);
		} else {
			result = type.getName();
		}

		return result;
	}
}
