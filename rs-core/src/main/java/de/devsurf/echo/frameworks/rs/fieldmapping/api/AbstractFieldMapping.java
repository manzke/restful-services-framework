package de.devsurf.echo.frameworks.rs.fieldmapping.api;
//package com.saperion.frameworks.rs.fieldmapping.api;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.saperion.common.lang.format.ExceptionFormatter;
//import com.saperion.frameworks.rs.fieldmapping.api.FieldValueConversion.FieldTypeMappingTupel;
//
///*
//for fields in metadata and extension. the values there are of type Object. so these mappings provide the types
//for the different layers (REST, UserManagement).
//the other values are specified concretely by their respective methods.
// */
//public abstract class AbstractFieldMapping {
//
//	//for the fieldName, from REST-Service type to other type
//	protected final Map<String, FieldTypeMappingTupel> fieldsMapping = new HashMap<>();
//
//	public boolean isMapped(String fieldName) {
//		return fieldsMapping.keySet().contains(fieldName);
//	}
//
//	//from other to REST-Service type
//	public FieldType getFieldTypeToRS(String fieldName) {
//		if (!isMapped(fieldName)) {
//			throw new IllegalArgumentException(ExceptionFormatter.format("field is not mapped.",
//					"item", getUserManagementItemType(), "field", fieldName));
//		}
//		return fieldsMapping.get(fieldName).type;
//	}
//
//	//from REST-Service type to other type
//	public FieldType getFieldTypeFromRS(String fieldName) {
//		if (!isMapped(fieldName)) {
//			throw new IllegalArgumentException(ExceptionFormatter.format("field is not mapped.",
//					"item", getUserManagementItemType(), "field", fieldName));
//		}
//		return fieldsMapping.get(fieldName).mappedType;
//	}
//
//	protected abstract String getUserManagementItemType();
//}
