package de.devsurf.echo.frameworks.rs.service.startup.guice;
//package com.saperion.frameworks.rs.service.startup.guice;
//
//import com.google.inject.AbstractModule;
//import com.saperion.frameworks.rs.system.api.ContextBinder;
//import com.saperion.frameworks.rs.system.api.ContextBinder.ContextualObject;
//
//public class GuicyfiedContextBinder extends AbstractModule implements ContextBinder {
//		private Class<? extends ContextualObject> contextualTypeClass;
//			
//		@Override
//		public ContextBinder bindContext(
//				Class<? extends ContextualObject> contextualTypeClass) {
//			this.contextualTypeClass = contextualTypeClass;
//			return this;
//		}
//
//		@Override
//		protected void configure() {
//			
////			Contexts.this.bindFactory(new Factory<T>() {
////				@Override
////				public T provide() {
////					return null;
////				}
////				
////				@Override
////				public void dispose(T instance) {
////					
////				}
////			});
//		}
//		
//	}