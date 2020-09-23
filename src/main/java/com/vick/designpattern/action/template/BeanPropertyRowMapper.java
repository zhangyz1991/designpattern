package com.vick.designpattern.action.template;

import com.sun.istack.internal.Nullable;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;

/**
 * @author Vick Zhang
 * @date 2020/9/3
 */
public class BeanPropertyRowMapper<T> implements RowMapper<T> {

    private static final Logger logger = LoggerFactory.getLogger(BeanPropertyRowMapper.class);

    private static final BigInteger LONG_MIN = BigInteger.valueOf(Long.MIN_VALUE);

    private static final BigInteger LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);

    /**
     * The class we are mapping to.
     */
    @Nullable
    private Class<T> mappedClass;
    /**
     * Map of the fields we provide mapping for.
     */
    @Nullable
    private Map<String, PropertyDescriptor> mappedFields;
    /**
     * Set of bean properties we provide mapping for.
     */
    @Nullable
    private Set<String> mappedProperties;

    private static final Map<Class<?>, Object> DEFAULT_TYPE_VALUES;

    static {
        Map<Class<?>, Object> values = new HashMap<>();
        values.put(boolean.class, false);
        values.put(byte.class, (byte) 0);
        values.put(short.class, (short) 0);
        values.put(int.class, 0);
        values.put(long.class, (long) 0);
        DEFAULT_TYPE_VALUES = Collections.unmodifiableMap(values);
    }


    public BeanPropertyRowMapper(Class<T> mappedClass) {
        initialize(mappedClass);
    }

    protected void initialize(Class<T> mappedClass) {
        this.mappedClass = mappedClass;
        this.mappedFields = new HashMap<>();
        this.mappedProperties = new HashSet<>();
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(mappedClass);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            if (pd.getWriteMethod() != null) {
                this.mappedFields.put(lowerCaseName(pd.getName()), pd);
                String underscoreName = underscoreName(pd.getName());
                if (!lowerCaseName(pd.getName()).equals(underscoreName)) {
                    this.mappedFields.put(underscoreName, pd);
                }
                this.mappedProperties.add(pd.getName());
            }
        }
    }

    /**
     * Convert the given name to lower case.
     * By default, conversions will happen within the US locale.
     *
     * @param name the original name
     * @return the converted name
     * @since 4.2
     */
    protected String lowerCaseName(String name) {
        return name.toLowerCase(Locale.US);
    }

    /**
     * Convert a name in camelCase to an underscored name in lower case.
     * Any upper case letters are converted to lower case with a preceding underscore.
     *
     * @param name the original name
     * @return the converted name
     * @see #lowerCaseName
     * @since 4.2
     */
    protected String underscoreName(String name) {
        if (!hasLength(name)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append(lowerCaseName(name.substring(0, 1)));
        for (int i = 1; i < name.length(); i++) {
            String s = name.substring(i, i + 1);
            String slc = lowerCaseName(s);
            if (!s.equals(slc)) {
                result.append("_").append(slc);
            } else {
                result.append(s);
            }
        }
        return result.toString();
    }

    @Override
    public T mapRow(ResultSet rs, int rowNumber) throws SQLException, IntrospectionException {
        ResultSetMetaData rsMetaData = rs.getMetaData();
        T mappedObject = instantiateClass(this.mappedClass);

        for (int index = 1; index <= rsMetaData.getColumnCount(); index++) {
            String column = lookupColumnName(rsMetaData, index);
            String field = lowerCaseName(delete(column, " "));
            PropertyDescriptor pd = (this.mappedFields != null ? this.mappedFields.get(field) : null);
            if (pd != null) {
                Object value = getColumnValue(rs, index, pd);
                try {
                    Method writeMethod = pd.getWriteMethod();
                    writeMethod.invoke(mappedObject, value);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println(rsMetaData.getColumnName(index) + " " + rsMetaData.getColumnClassName(index));
        }
        return mappedObject;
    }

    @Nullable
    protected Object getColumnValue(ResultSet rs, int index, PropertyDescriptor pd) throws SQLException {
        return getResultSetValue(rs, index, pd.getPropertyType());
    }

    /**
     * Retrieve a JDBC column value from a ResultSet, using the specified value type.
     * <p>Uses the specifically typed ResultSet accessor methods, falling back to
     * {@link #getResultSetValue(java.sql.ResultSet, int)} for unknown types.
     * <p>Note that the returned value may not be assignable to the specified
     * required type, in case of an unknown type. Calling code needs to deal
     * with this case appropriately, e.g. throwing a corresponding exception.
     *
     * @param rs           is the ResultSet holding the data
     * @param index        is the column index
     * @param requiredType the required value type (may be {@code null})
     * @return the value object (possibly not of the specified required type,
     * with further conversion steps necessary)
     * @throws SQLException if thrown by the JDBC API
     * @see #getResultSetValue(ResultSet, int)
     */
    @Nullable
    public static Object getResultSetValue(ResultSet rs, int index, @Nullable Class<?> requiredType) throws SQLException {
        if (requiredType == null) {
            return getResultSetValue(rs, index);
        }

        Object value;

        // Explicitly extract typed value, as far as possible.
        if (String.class == requiredType) {
            return rs.getString(index);
        } else if (boolean.class == requiredType || Boolean.class == requiredType) {
            value = rs.getBoolean(index);
        } else if (byte.class == requiredType || Byte.class == requiredType) {
            value = rs.getByte(index);
        } else if (short.class == requiredType || Short.class == requiredType) {
            value = rs.getShort(index);
        } else if (int.class == requiredType || Integer.class == requiredType) {
            value = rs.getInt(index);
        } else if (long.class == requiredType || Long.class == requiredType) {
            value = rs.getLong(index);
        } else if (float.class == requiredType || Float.class == requiredType) {
            value = rs.getFloat(index);
        } else if (double.class == requiredType || Double.class == requiredType ||
                Number.class == requiredType) {
            value = rs.getDouble(index);
        } else if (BigDecimal.class == requiredType) {
            return rs.getBigDecimal(index);
        } else if (java.sql.Date.class == requiredType) {
            return rs.getDate(index);
        } else if (java.sql.Time.class == requiredType) {
            return rs.getTime(index);
        } else if (java.sql.Timestamp.class == requiredType || java.util.Date.class == requiredType) {
            return rs.getTimestamp(index);
        } else if (byte[].class == requiredType) {
            return rs.getBytes(index);
        } else if (Blob.class == requiredType) {
            return rs.getBlob(index);
        } else if (Clob.class == requiredType) {
            return rs.getClob(index);
        } else if (requiredType.isEnum()) {
            // Enums can either be represented through a String or an enum index value:
            // leave enum type conversion up to the caller (e.g. a ConversionService)
            // but make sure that we return nothing other than a String or an Integer.
            Object obj = rs.getObject(index);
            if (obj instanceof String) {
                return obj;
            } else if (obj instanceof Number) {
                // Defensively convert any Number to an Integer (as needed by our
                // ConversionService's IntegerToEnumConverterFactory) for use as index
                return convertNumberToTargetClass((Number) obj, Integer.class);
            } else {
                // e.g. on Postgres: getObject returns a PGObject but we need a String
                return rs.getString(index);
            }
        } else {
            // Some unknown type desired -> rely on getObject.
            try {
                return rs.getObject(index, requiredType);
            } catch (AbstractMethodError err) {
                logger.debug("JDBC driver does not implement JDBC 4.1 'getObject(int, Class)' method", err);
            } catch (SQLFeatureNotSupportedException ex) {
                logger.debug("JDBC driver does not support JDBC 4.1 'getObject(int, Class)' method", ex);
            } catch (SQLException ex) {
                logger.debug("JDBC driver has limited support for JDBC 4.1 'getObject(int, Class)' method", ex);
            }

            // Corresponding SQL types for JSR-310 / Joda-Time types, left up
            // to the caller to convert them (e.g. through a ConversionService).
            String typeName = requiredType.getSimpleName();
            if ("LocalDate".equals(typeName)) {
                return rs.getDate(index);
            } else if ("LocalTime".equals(typeName)) {
                return rs.getTime(index);
            } else if ("LocalDateTime".equals(typeName)) {
                return rs.getTimestamp(index);
            }

            // Fall back to getObject without type specification, again
            // left up to the caller to convert the value if necessary.
            return getResultSetValue(rs, index);
        }

        // Perform was-null check if necessary (for results that the JDBC driver returns as primitives).
        return (rs.wasNull() ? null : value);
    }

    /**
     * Convert the given number into an instance of the given target class.
     *
     * @param number      the number to convert
     * @param targetClass the target class to convert to
     * @return the converted number
     * @throws IllegalArgumentException if the target class is not supported
     *                                  (i.e. not a standard Number subclass as included in the JDK)
     * @see java.lang.Byte
     * @see java.lang.Short
     * @see java.lang.Integer
     * @see java.lang.Long
     * @see java.math.BigInteger
     * @see java.lang.Float
     * @see java.lang.Double
     * @see java.math.BigDecimal
     */
    @SuppressWarnings("unchecked")
    public static <T extends Number> T convertNumberToTargetClass(Number number, Class<T> targetClass)
            throws IllegalArgumentException {

        Assert.notNull(number, "Number must not be null");
        Assert.notNull(targetClass, "Target class must not be null");

        if (targetClass.isInstance(number)) {
            return (T) number;
        } else if (Byte.class == targetClass) {
            long value = checkedLongValue(number, targetClass);
            if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE) {
                raiseOverflowException(number, targetClass);
            }
            return (T) Byte.valueOf(number.byteValue());
        } else if (Short.class == targetClass) {
            long value = checkedLongValue(number, targetClass);
            if (value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
                raiseOverflowException(number, targetClass);
            }
            return (T) Short.valueOf(number.shortValue());
        } else if (Integer.class == targetClass) {
            long value = checkedLongValue(number, targetClass);
            if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
                raiseOverflowException(number, targetClass);
            }
            return (T) Integer.valueOf(number.intValue());
        } else if (Long.class == targetClass) {
            long value = checkedLongValue(number, targetClass);
            return (T) Long.valueOf(value);
        } else if (BigInteger.class == targetClass) {
            if (number instanceof BigDecimal) {
                // do not lose precision - use BigDecimal's own conversion
                return (T) ((BigDecimal) number).toBigInteger();
            } else {
                // original value is not a Big* number - use standard long conversion
                return (T) BigInteger.valueOf(number.longValue());
            }
        } else if (Float.class == targetClass) {
            return (T) Float.valueOf(number.floatValue());
        } else if (Double.class == targetClass) {
            return (T) Double.valueOf(number.doubleValue());
        } else if (BigDecimal.class == targetClass) {
            // always use BigDecimal(String) here to avoid unpredictability of BigDecimal(double)
            // (see BigDecimal javadoc for details)
            return (T) new BigDecimal(number.toString());
        } else {
            throw new IllegalArgumentException("Could not convert number [" + number + "] of type [" +
                    number.getClass().getName() + "] to unsupported target class [" + targetClass.getName() + "]");
        }
    }

    /**
     * Check for a {@code BigInteger}/{@code BigDecimal} long overflow
     * before returning the given number as a long value.
     *
     * @param number      the number to convert
     * @param targetClass the target class to convert to
     * @return the long value, if convertible without overflow
     * @throws IllegalArgumentException if there is an overflow
     * @see #raiseOverflowException
     */
    private static long checkedLongValue(Number number, Class<? extends Number> targetClass) {
        BigInteger bigInt = null;
        if (number instanceof BigInteger) {
            bigInt = (BigInteger) number;
        } else if (number instanceof BigDecimal) {
            bigInt = ((BigDecimal) number).toBigInteger();
        }
        // Effectively analogous to JDK 8's BigInteger.longValueExact()
        if (bigInt != null && (bigInt.compareTo(LONG_MIN) < 0 || bigInt.compareTo(LONG_MAX) > 0)) {
            raiseOverflowException(number, targetClass);
        }
        return number.longValue();
    }

    /**
     * Raise an <em>overflow</em> exception for the given number and target class.
     *
     * @param number      the number we tried to convert
     * @param targetClass the target class we tried to convert to
     * @throws IllegalArgumentException if there is an overflow
     */
    private static void raiseOverflowException(Number number, Class<?> targetClass) {
        throw new IllegalArgumentException("Could not convert number [" + number + "] of type [" +
                number.getClass().getName() + "] to target class [" + targetClass.getName() + "]: overflow");
    }

    /**
     * Retrieve a JDBC column value from a ResultSet, using the most appropriate
     * value type. The returned value should be a detached value object, not having
     * any ties to the active ResultSet: in particular, it should not be a Blob or
     * Clob object but rather a byte array or String representation, respectively.
     * <p>Uses the {@code getObject(index)} method, but includes additional "hacks"
     * to get around Oracle 10g returning a non-standard object for its TIMESTAMP
     * datatype and a {@code java.sql.Date} for DATE columns leaving out the
     * time portion: These columns will explicitly be extracted as standard
     * {@code java.sql.Timestamp} object.
     *
     * @param rs    is the ResultSet holding the data
     * @param index is the column index
     * @return the value object
     * @throws SQLException if thrown by the JDBC API
     * @see java.sql.Blob
     * @see java.sql.Clob
     * @see java.sql.Timestamp
     */
    @Nullable
    public static Object getResultSetValue(ResultSet rs, int index) throws SQLException {
        Object obj = rs.getObject(index);
        String className = null;
        if (obj != null) {
            className = obj.getClass().getName();
        }
        if (obj instanceof Blob) {
            Blob blob = (Blob) obj;
            obj = blob.getBytes(1, (int) blob.length());
        } else if (obj instanceof Clob) {
            Clob clob = (Clob) obj;
            obj = clob.getSubString(1, (int) clob.length());
        } else if ("oracle.sql.TIMESTAMP".equals(className) || "oracle.sql.TIMESTAMPTZ".equals(className)) {
            obj = rs.getTimestamp(index);
        } else if (className != null && className.startsWith("oracle.sql.DATE")) {
            String metaDataClassName = rs.getMetaData().getColumnClassName(index);
            if ("java.sql.Timestamp".equals(metaDataClassName) || "oracle.sql.TIMESTAMP".equals(metaDataClassName)) {
                obj = rs.getTimestamp(index);
            } else {
                obj = rs.getDate(index);
            }
        } else if (obj instanceof java.sql.Date) {
            if ("java.sql.Timestamp".equals(rs.getMetaData().getColumnClassName(index))) {
                obj = rs.getTimestamp(index);
            }
        }
        return obj;
    }

    public static String delete(String inString, String pattern) {
        return replace(inString, pattern, "");
    }

    public static String replace(String inString, String oldPattern, @Nullable String newPattern) {
        if (hasLength(inString) && hasLength(oldPattern) && newPattern != null) {
            int index = inString.indexOf(oldPattern);
            if (index == -1) {
                return inString;
            } else {
                int capacity = inString.length();
                if (newPattern.length() > oldPattern.length()) {
                    capacity += 16;
                }

                StringBuilder sb = new StringBuilder(capacity);
                int pos = 0;

                for (int patLen = oldPattern.length(); index >= 0; index = inString.indexOf(oldPattern, pos)) {
                    sb.append(inString, pos, index);
                    sb.append(newPattern);
                    pos = index + patLen;
                }

                sb.append(inString, pos, inString.length());
                return sb.toString();
            }
        } else {
            return inString;
        }
    }

    /**
     * Determine the column name to use. The column name is determined based on a
     * lookup using ResultSetMetaData.
     * <p>This method implementation takes into account recent clarifications
     * expressed in the JDBC 4.0 specification:
     * <p><i>columnLabel - the label for the column specified with the SQL AS clause.
     * If the SQL AS clause was not specified, then the label is the name of the column</i>.
     *
     * @param resultSetMetaData the current meta-data to use
     * @param columnIndex       the index of the column for the look up
     * @return the column name to use
     * @throws SQLException in case of lookup failure
     */
    public static String lookupColumnName(ResultSetMetaData resultSetMetaData, int columnIndex) throws SQLException {
        String name = resultSetMetaData.getColumnLabel(columnIndex);
        if (!hasLength(name)) {
            name = resultSetMetaData.getColumnName(columnIndex);
        }
        return name;
    }

    private static boolean hasLength(String str) {
        return str != null && str.length() != 0;
    }

    public static <T> T instantiateClass(Class<T> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        if (clazz.isInterface()) {
            throw new RuntimeException("Failed to instantiate [" + clazz.getName() + "]: " + "Specified class is an interface");
        }
        try {
            return instantiateClass(clazz.getDeclaredConstructor());
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException("Failed to instantiate [" + clazz.getName() + "]: " + "No default constructor found", ex);
        } catch (LinkageError err) {
            throw new RuntimeException("Failed to instantiate [" + clazz.getName() + "]: " + "Unresolvable class definition", err);
        }
    }

    public static <T> T instantiateClass(Constructor<T> ctor, Object... args) {
        Assert.notNull(ctor, "Constructor must not be null");
        try {
            makeAccessible(ctor);

            Class<?>[] parameterTypes = ctor.getParameterTypes();
            Assert.isTrue(args.length <= parameterTypes.length, "Can't specify more arguments than constructor parameters");
            Object[] argsWithDefaultValues = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                if (args[i] == null) {
                    Class<?> parameterType = parameterTypes[i];
                    argsWithDefaultValues[i] = (parameterType.isPrimitive() ? DEFAULT_TYPE_VALUES.get(parameterType) : null);
                } else {
                    argsWithDefaultValues[i] = args[i];
                }
            }
            return ctor.newInstance(argsWithDefaultValues);

        } catch (InstantiationException ex) {
            throw new RuntimeException("Failed to instantiate [" + ctor.getName() + "]: " + "Is it an abstract class?", ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException("Failed to instantiate [" + ctor.getName() + "]: " + "Is the constructor accessible?", ex);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Failed to instantiate [" + ctor.getName() + "]: " + "Illegal arguments for constructor", ex);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException("Failed to instantiate [" + ctor.getName() + "]: " + "Constructor threw exception", ex.getTargetException());
        }
    }

    public static void makeAccessible(Constructor<?> ctor) {
        if ((!Modifier.isPublic(ctor.getModifiers()) ||
                !Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) && !ctor.isAccessible()) {
            ctor.setAccessible(true);
        }
    }
}
