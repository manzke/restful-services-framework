package de.devsurf.echo.frameworks.rs.api;

public class Versions {
	public final static Version UNKNOWN_VERSION = new DefaultVersion(0, 0, 0) {
		public boolean isUnknown() {
			return true;
		};
	};

	public static Version unknownVersion() {
		return UNKNOWN_VERSION;
	}

	public static Version version(int major, int minor, int patchLevel) {
		return new DefaultVersion(major, minor, patchLevel);
	}

	protected static class DefaultVersion implements Comparable<Version>,
			Version {

		private int major;

		private int minor;

		private int patchLevel;

		protected DefaultVersion(int major, int minor, int patchLevel) {
			this.major = major;
			this.minor = minor;
			this.patchLevel = patchLevel;
		}

		@Override
		public boolean isUnknown() {
			return false;
		}

		@Override
		public int getMajor() {
			return major;
		}

		@Override
		public int getMinor() {
			return minor;
		}

		@Override
		public int getPatchLevel() {
			return patchLevel;
		}

		@Override
		public Type type() {
			return VERSION_TYPE;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			if (isUnknown()) {
				sb.append("version is unknown");
			} else {
				sb.append(major).append('.');
				sb.append(minor).append('.');
				sb.append(patchLevel);
			}

			return sb.toString();
		}

		@Override
		public int hashCode() {
			return major + minor + patchLevel + (isUnknown() ? -1 : 0);
		}

		@Override
		public boolean equals(Object o) {
			if (o == this)
				return true;
			if (o == null)
				return false;
			if (o.getClass() != getClass())
				return false;
			DefaultVersion other = (DefaultVersion) o;
			return (other.major == major) && (other.minor == minor)
					&& (other.patchLevel == patchLevel) && (other.isUnknown() == isUnknown());
		}

		@Override
		public int compareTo(Version other) {
			int diff = major - other.getMajor();
			if (diff == 0) {
				diff = minor - other.getMinor();
				if (diff == 0) {
					diff = patchLevel - other.getPatchLevel();
				}
			}
			return diff;
		}
	}
}
