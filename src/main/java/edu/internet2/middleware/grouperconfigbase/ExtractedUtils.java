package edu.internet2.middleware.grouperconfigbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractedUtils {
	  /**
	   * null safe string compare
	   * @param first
	   * @param second
	   * @return true if equal
	   */
	  public static boolean equals(String first, String second) {
	    if (first == second) {
	      return true;
	    }
	    if (first == null || second == null) {
	      return false;
	    }
	    return first.equals(second);
	  }

	  /**
	   * <p>Checks if a String is whitespace, empty ("") or null.</p>
	   *
	   * <pre>
	   * isBlank(null)      = true
	   * isBlank("")        = true
	   * isBlank(" ")       = true
	   * isBlank("bob")     = false
	   * isBlank("  bob  ") = false
	   * </pre>
	   *
	   * @param str  the String to check, may be null
	   * @return <code>true</code> if the String is null, empty or whitespace
	   * @since 2.0
	   */
	  public static boolean isBlank(String str) {
	    int strLen;
	    if (str == null || (strLen = str.length()) == 0) {
	      return true;
	    }
	    for (int i = 0; i < strLen; i++) {
	      if ((Character.isWhitespace(str.charAt(i)) == false)) {
	        return false;
	      }
	    }
	    return true;
	  }
	  
	  /**
	   * trim whitespace from string
	   * @param str
	   * @return trimmed string
	   */
	  public static String trim(String str) {
	    return str == null ? null : str.trim();
	  }
	  
	  /**
	   * <p>Returns either the passed in String,
	   * or if the String is <code>null</code>, an empty String ("").</p>
	   *
	   * <pre>
	   * StringUtils.defaultString(null)  = ""
	   * StringUtils.defaultString("")    = ""
	   * StringUtils.defaultString("bat") = "bat"
	   * </pre>
	   *
	   * @see String#valueOf(Object)
	   * @param str  the String to check, may be null
	   * @return the passed in String, or the empty String if it
	   *  was <code>null</code>
	   */
	  public static String defaultString(String str) {
	    return str == null ? "" : str;
	  }
	  
	  /**
	   * pattern to get the file path or resource location for a file
	   */
	  private static Pattern fileLocationPattern = Pattern.compile("^(file|classpath)\\s*:(.*)$");
	  
	  /**
	   * file or classpath location
	   * @param typeAndLocation
	   * @return the inputstream
	   */
	  public static InputStream fileOrClasspathInputstream(String typeAndLocation, String logHint) {
	    Matcher matcher = fileLocationPattern.matcher(typeAndLocation);
	    if (!matcher.matches()) {
	      throw new RuntimeException(logHint + " must start with file: or classpath:");
	    }
	    String typeString = matcher.group(1);
	    String location = trim(matcher.group(2));
	    
	    if (equals(typeString, "file")) {
	      File file = new File(location);
	      if (!file.exists() || !file.isFile()) {
	        throw new RuntimeException(logHint + " File does not exist: " + file.getAbsolutePath());
	      }
	      try {
	        return new FileInputStream(file);
	      } catch (Exception e) {
	        throw new RuntimeException(logHint + " Problem with file: " + file.getAbsolutePath());
	      }
	    } else if (equals(typeString, "classpath")) {
	      if (!location.startsWith("/")) {
	        location = "/" + location;
	      }
	      try {
	        return ExtractedUtils.class.getResourceAsStream(location);
	      } catch (Exception e) {
	        throw new RuntimeException(logHint + " Problem with classpath location: " + location);
	      }
	    } else {
	      throw new RuntimeException(logHint + " Not expecting type string: " + typeString);
	    }
	    
	}
	  
	  /**
	   * replace a string or strings from a string, and put the output in a string
	   * buffer. This does not recurse
	   * 
	   * @param text
	   *            string to look in
	   * @param searchFor
	   *            string array to search for
	   * @param replaceWith
	   *            string array to replace with
	   * @return the string
	   */
	  public static String replace(String text, Object searchFor,
	      Object replaceWith) {
		  // TODO: FIXME
		  // intentional no-op, these dependencies need to be tracked down and pulled out
	    return text;
	}
}
