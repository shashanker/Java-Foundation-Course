package com.epam.course.taglib.json.util;

public class JSONTokener {
	private int myIndex;

	private String mySource;

	public JSONTokener(String s) {
		myIndex = 0;
		mySource = s;
	}

	public void back() {
		if (myIndex > 0) {
			myIndex -= 1;
		}
	}

	public static int dehexchar(char c) {
		if ((c >= '0') && (c <= '9')) {
			return c - '0';
		}
		if ((c >= 'A') && (c <= 'F')) {
			return c - '7';
		}
		if ((c >= 'a') && (c <= 'f')) {
			return c - 'W';
		}
		return -1;
	}

	public boolean more() {
		return myIndex < mySource.length();
	}

	public char next() {
		if (more()) {
			char c = mySource.charAt(myIndex);
			myIndex += 1;
			return c;
		}
		return '\000';
	}

	public char next(char c) throws JSONException {
		char n = next();
		if (n != c) {
			throw syntaxError("Expected '" + c + "' and instead saw '" + n + "'.");
		}

		return n;
	}

	public String next(int n) throws JSONException {
		int i = myIndex;
		int j = i + n;
		if (j >= mySource.length()) {
			throw syntaxError("Substring bounds error");
		}
		myIndex += n;
		return mySource.substring(i, j);
	}

	public char nextClean() throws JSONException {
		for (;;) {
			char c = next();
			if (c == '/') {
				switch (next()) {
				case '/':
					do {
						c = next();
						if ((c == '\n') || (c == '\r'))
							break;
					} while (c != 0);
					break;
				case '*':
					for (;;) {
						c = next();
						if (c == 0) {
							throw syntaxError("Unclosed comment.");
						}
						if (c == '*') {
							if (next() == '/') {
								break;
							}
							back();
						}
					}

				default:
					back();
					return '/';
				}
			} else if (c == '#') {
				do {
					c = next();
					if ((c == '\n') || (c == '\r'))
						break;
				} while (c != 0);
			} else if ((c == 0) || (c > ' ')) {
				return c;
			}
		}
	}

	public String nextString(char quote) throws JSONException {
		StringBuffer sb = new StringBuffer();
		for (;;) {
			char c = next();
			switch (c) {
			case '\000':
			case '\n':
			case '\r':
				throw syntaxError("Unterminated string");
			case '\\':
				c = next();
				switch (c) {
				case 'b':
					sb.append('\b');
					break;
				case 't':
					sb.append('\t');
					break;
				case 'n':
					sb.append('\n');
					break;
				case 'f':
					sb.append('\f');
					break;
				case 'r':
					sb.append('\r');
					break;
				case 'u':
					sb.append((char) Integer.parseInt(next(4), 16));
					break;
				case 'x':
					sb.append((char) Integer.parseInt(next(2), 16));
					break;
				case 'c':
				case 'd':
				case 'e':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'o':
				case 'p':
				case 'q':
				case 's':
				case 'v':
				case 'w':
				default:
					sb.append(c);
				}
				break;
			default:
				if (c == quote) {
					return sb.toString();
				}
				sb.append(c);
			}

		}
	}

	public String nextTo(char d) {
		StringBuffer sb = new StringBuffer();
		for (;;) {
			char c = next();
			if ((c == d) || (c == 0) || (c == '\n') || (c == '\r')) {
				if (c != 0) {
					back();
				}
				return sb.toString().trim();
			}
			sb.append(c);
		}
	}

	public String nextTo(String delimiters) {
		StringBuffer sb = new StringBuffer();
		for (;;) {
			char c = next();
			if ((delimiters.indexOf(c) >= 0) || (c == 0) || (c == '\n') || (c == '\r')) {
				if (c != 0) {
					back();
				}
				return sb.toString().trim();
			}
			sb.append(c);
		}
	}

	public Object nextValue() throws JSONException {
		char c = nextClean();

		switch (c) {
		case '"':
		case '\'':
			return nextString(c);
		case '{':
			back();
			return new JSONObject(this);
		case '[':
			back();
			return new JSONArray(this);
		}

		StringBuffer sb = new StringBuffer();
		char b = c;
		while ((c >= ' ') && (",:]}/\\\"[{;=#".indexOf(c) < 0)) {
			sb.append(c);
			c = next();
		}
		back();

		String s = sb.toString().trim();
		if (s.equals("")) {
			throw syntaxError("Missing value.");
		}
		if (s.equalsIgnoreCase("true")) {
			return Boolean.TRUE;
		}
		if (s.equalsIgnoreCase("false")) {
			return Boolean.FALSE;
		}
		if (s.equalsIgnoreCase("null")) {
			return null;
		}

		if (((b >= '0') && (b <= '9')) || (b == '.') || (b == '-') || (b == '+')) {
			if (b == '0') {
				if ((s.length() > 2) && ((s.charAt(1) == 'x') || (s.charAt(1) == 'X'))) {
					try {
						return Integer.valueOf(s.substring(2), 16);
					} catch (Exception e) {
					}
				} else {
					try {
						return Integer.valueOf(s, 8);
					} catch (Exception e) {
					}
				}
			}
			try {
				return new Integer(s);
			} catch (Exception e) {
				try {
					return new Long(s);
				} catch (Exception f) {
					try {
						return new Double(s);
					} catch (Exception g) {
						return s;
					}
				}
			}
		}
		return s;
	}

	public char skipTo(char to) {
		int index = myIndex;
		char c;
		do {
			c = next();
			if (c == 0) {
				myIndex = index;
				return c;
			}
		} while (c != to);
		back();
		return c;
	}

	public void skipPast(String to) {
		myIndex = mySource.indexOf(to, myIndex);
		if (myIndex < 0) {
			myIndex = mySource.length();
		} else {
			myIndex += to.length();
		}
	}

	public JSONException syntaxError(String message) {
		return new JSONException(message + toString());
	}

	public String toString() {
		return " at character " + myIndex + " of " + mySource;
	}
}
