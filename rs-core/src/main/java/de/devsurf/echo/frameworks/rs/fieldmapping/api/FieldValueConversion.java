package de.devsurf.echo.frameworks.rs.fieldmapping.api;

public class FieldValueConversion {

	public static class FieldTypeMappingTupel {
		public final FieldType type;
		public final FieldType mappedType;

		public FieldTypeMappingTupel(FieldType type, FieldType mappedType) {
			this.type = type;
			this.mappedType = mappedType;
		}
	}

	private String valueAsString;

	private FieldValueConversion(Object value) {
		valueAsString = String.valueOf(value);
	}

	public <T> T to(FieldType fieldType) {

		if (fieldType == null) {
			throw new IllegalArgumentException("No fieldType given");
		}

		if (fieldType == FieldType.STRING) {
			return (T) valueAsString;
		} else if (fieldType == FieldType.INTEGER) {
			return (T) Integer.valueOf(valueAsString);
		} else if (fieldType == FieldType.BOOLEAN) {
			return (T) Boolean.valueOf(valueAsString);
		} else {
			throw new IllegalArgumentException("conversion not implemented to "
					+ fieldType.toString());
		}
	}

	public static FieldValueConversion convert(Object value) {

		if (value == null) {
			throw new IllegalArgumentException("Value to convert is null or empty");
		}
		return new FieldValueConversion(value);
	}
}
