package hubble.samba.util;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import hubble.samba.model.SambaProperties;
import org.apache.log4j.Logger;

public class ResourceBundleUtil extends ResourceBundle {
	static  ResourceBundle bundle;
	private static Logger logger = Logger.getLogger(ResourceBundleUtil.class.getName());

	public ResourceBundleUtil() {
	
	}
	
	public ResourceBundleUtil(String resourceFile) {
	
		bundle = ResourceBundle.getBundle(resourceFile);
		logger.debug("Loading resource bundle from : " +getClass().getClassLoader().getResource(resourceFile +".properties"));

	}

//	public static ResourceBundle getResourceBundle(String resourceFile)
//	{
//		bundle = ResourceBundle.getBundle(resourceFile);
//		return bundle;
//	}
	/**
	 * This method  returns a formatted String by replacing placeholders with param StringArray
	 * @param key
	 * @param param
	 * @return
	 */
	public final String getString(String key, String[] params)
	{
		String value = getString(key);
		value=escapeMessageFormat(key);
		String fString = MessageFormat.format(value, (Object[])params);
		return fString;
	}
	/**
	 * This method  returns a formatted String by replacing placeholders with param String
	 * @param key
	 * @param param
	 * @return
	 */
	public  String getString(String key, String param)
	{
		String value = getString(key);	

		value=escapeMessageFormat(key);
		String fString = MessageFormat.format(value, (Object[]) new String[] {param});
		return fString;
	}
	
	/**
	 * This method  returns a formatted String by replacing placeholders with param StringArray
	 * @param key
	 * @param param
	 * @return
	 */
	public final String generateXpath(String key, String[] params)
	{
		String value=escapeMessageFormat(key);
		String fString = MessageFormat.format(value, (Object[])params);
		return fString;
	}
	/**
	 * This method  returns a formatted String by replacing placeholders with param String
	 * @param key
	 * @param param
	 * @return
	 */
	public  String generateXpath(String key, String param)
	{

		String value=escapeMessageFormat(key);
		String fString = MessageFormat.format(value, (Object[]) new String[] {param});
		return fString;
	}


	/**
	 * This method is used to replace single quote with double quotes
	 * @param str
	 * @return
	 */
	public static String escapeMessageFormat(String str)
	{
		//str=str.replace("\"", "\"");
		str=str.replace("'", "''");
		return str;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ResourceBundle#getKeys()
	 */
	@Override
	public Enumeration<String> getKeys() {
		return new Enumeration<String>() {
			// Coding Comment: I must eliminate duplicate keys from multiple
			// bundles so I take the easy way out and create a set of keys over
			// all bundles. This is memory-intensive, O(# keys) PER Enumeration!
			// We could create a static set of keys for all enumerations and
			// refresh on clearCache(). Seems like a bloom filter would be
			// better, but...

			// Per enumeration list of keys from all bundles
			private Set<String> keys = new HashSet<String>();


			// Set iterator to simulate enumeration
			private Iterator<String> i;

			// Constructor
			{
				keys.addAll(bundle.keySet());

				i = keys.iterator();
			}

			public boolean hasMoreElements() {
				return i.hasNext();
			}

			public String nextElement() {
				return i.next();
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ResourceBundle#handleGetObject(java.lang.String)
	 */
	@Override
	protected Object handleGetObject(String key) {
		if (key == null) {
			throw new NullPointerException();
		}
		// Search for first resource bundle containing the specified key

		try {
			return bundle.getObject(key);
		} catch (MissingResourceException mre) {
		}

		return null;
	}
	
	public static boolean compareStrLists(ArrayList<String> list1, ArrayList<String> list2) {
		boolean cFlag = true;
		ArrayList<String> tempList = new ArrayList<String>();
		tempList.addAll(list1);
		tempList.removeAll(list2);
		list2.removeAll(list1);
		
		if(tempList.isEmpty() && list2.isEmpty()) {
			cFlag = true;
		} else {
			cFlag = false;
		}
		return cFlag;
	}
	
	public static void waitForPageToLoad(String timeLimit){
		String timeout = SambaProperties.configDetails.getProperty(timeLimit);
		DriverUtilities.waitForPageToLoad(timeout);
	}
	
	
}
