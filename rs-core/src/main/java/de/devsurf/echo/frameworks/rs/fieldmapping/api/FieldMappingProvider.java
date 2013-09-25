package de.devsurf.echo.frameworks.rs.fieldmapping.api;
//package com.saperion.frameworks.rs.fieldmapping.api;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.saperion.components.users.service.models.ModelType;
//
//public class FieldMappingProvider {
//
//	private static final Map<ModelType, AbstractFieldMapping> itemMappings = new HashMap<>();
//	static {
//		itemMappings.put(ModelType.USER, new UserFieldTypeMapping());
//		itemMappings.put(ModelType.GROUP, new ExampleFieldTypeMapping());
//		itemMappings.put(ModelType.TENANT, new TenantFieldTypeMapping());
//		itemMappings.put(ModelType.ROLE, new ProfileFieldTypeMapping());
//	}
//
//	public static AbstractFieldMapping byItemType(ModelType type) {
//
//		AbstractFieldMapping mapping = itemMappings.get(type);
//		if (mapping == null) {
//			throw new IllegalArgumentException("No fieldtype-mapping found for " + type.name());
//		}
//		return mapping;
//	}
//}
