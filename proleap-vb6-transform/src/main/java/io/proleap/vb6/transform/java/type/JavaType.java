package io.proleap.vb6.transform.java.type;

public enum JavaType {
	BIGDECIMAL("BigDecimal"), BOOLEAN("Boolean"), BYTE("Byte"), COLLECTION("VbCollection"), COLOR("VbColor"),
	DATE("java.util.Date"), DOUBLE("Double"), DYNAMIC_ARRAY("VbDynamicArray"), INTEGER("Integer"), LONG("Long"),
	NUMBER("Number"), OBJECT("Object"), SINGLE("Double"), STATIC_ARRAY("VbStaticArray"), STRING("String"),
	VALUE_ENUM("VbValueEnum"), VB_FILE_NUMBER("VbFileNumber");

	protected final String name;

	private JavaType(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
