package dk.sdu.mmmi.cbse.common.util;

import java.util.*;

/**
 * This class locates all implementations of a given service interface using the
 * Service Provider Interface (SPI) mechanism.
 */
public class SPILocator {

	@SuppressWarnings("rawtypes")
	private static final Map<Class, ServiceLoader> loadermap = new HashMap<Class, ServiceLoader>();

	private SPILocator() {}

	/**
	 * Locates all implementations of the given service interface.
	 *
	 * @param service The service interface to locate implementations of.
	 * @return A list of all implementations of the given service interface.
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> locateAll(Class<T> service) {
		ServiceLoader<T> loader = loadermap.get(service);

		boolean printStatement = false;

		if (loader == null) {
			loader = ServiceLoader.load(service);
			loadermap.put(service, loader);
			printStatement = true;
		}

		List<T> list = new ArrayList<T>();

		if (loader != null) {
			try {
				for (T instance : loader) {
					list.add(instance);
				}
			} catch (ServiceConfigurationError serviceError) {
				serviceError.printStackTrace();
			}
		}

		if (printStatement) {
			System.out.println("Found: " + list.size() + " implementations for interface: " + service.getName());
		}

		return list;
	}
}