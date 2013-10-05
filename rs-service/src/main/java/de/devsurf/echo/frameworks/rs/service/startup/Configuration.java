package de.devsurf.echo.frameworks.rs.service.startup;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.google.inject.binder.ConstantBindingBuilder;
import com.google.inject.name.Names;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;

import de.devsurf.echo.frameworks.rs.service.exceptions.CorruptConfigurationException;

public class Configuration implements Module {
	
	public static final TypeLiteral<List<String>> LIST = new TypeLiteral<List<String>>() {
	};
	public static final Path DEFAULT_PATH = null;

	private final Path path;
	private final Namespace namespace;

	public Configuration() {
		this(Namespace.standard(), DEFAULT_PATH);
	}

	public Configuration(Namespace namespace) {
		this(namespace, DEFAULT_PATH);
	}

	public Configuration(Path path) {
		this(Namespace.standard(), path);
	}

	public Configuration(Namespace namespace, Path path) {
		Preconditions.checkNotNull(namespace,
				"Namespace for the configuration root can't be null.");
		this.path = path;
		this.namespace = namespace;
	}

	@SuppressWarnings("unchecked")
	public void configure(Binder binder) {
		Config configuration;
		if (path != null) {
			if (!Files.exists(path)) {
				throw new CorruptConfigurationException(
						"Configuration couldn't be found at: " + path + " ("
								+ path + ")");
			}
			try (BufferedReader reader = Files.newBufferedReader(path,
					Charset.forName("UTF-8"))) {
				configuration = ConfigFactory.parseReader(reader).resolve()
						.getConfig(namespace.name());
			} catch (IOException e) {
				throw new CorruptConfigurationException(
						"Configuration couldn't be loaded at \"" + path + "\".",
						e);
			}
		} else {
			configuration = ConfigFactory.load().getConfig(namespace.name());
		}

		Set<Entry<String, ConfigValue>> entries = configuration.entrySet();
		for (Entry<String, ConfigValue> entry : entries) {
			ConfigValue value = entry.getValue();
			if (value.valueType() == ConfigValueType.LIST) {
				binder.bind(LIST)
						.annotatedWith(Names.named(entry.getKey()))
						.toInstance((List<String>) entry.getValue().unwrapped());
			} else {
				ConstantBindingBuilder annotatedWith = binder.bindConstant()
						.annotatedWith(Names.named(entry.getKey()));
				annotatedWith.to(value.unwrapped().toString());
			}
		}
	}

	@Override
	public String toString() {
		return "Configuration [path=" + path + ", namespace=" + namespace + "]";
	}

	public static class Namespace {
		private static final String DEFAULT_NAMESPACE = "service";

		private final String name;

		private Namespace(String name) {
			this.name = name;
		}

		public static Namespace standard() {
			return new Namespace(DEFAULT_NAMESPACE);
		}

		public String name() {
			return name;
		}

		@Override
		public String toString() {
			return "Namespace [name=" + name + "]";
		}
	}
}
