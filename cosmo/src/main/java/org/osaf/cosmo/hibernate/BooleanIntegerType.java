/*
 * Copyright 2006 Open Source Applications Foundation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.osaf.cosmo.hibernate;

import org.hibernate.type.BooleanType;
import org.hibernate.type.descriptor.java.BooleanTypeDescriptor;
import org.hibernate.type.descriptor.sql.SmallIntTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

/**
 * Custom hibernate type that persists a java boolean
 * to an integer field.
 *
 */
/**
 * A type that maps between {@link java.sql.Types#SMALLINT} and {@link Boolean}
 */
public class BooleanIntegerType extends BooleanType {

	public static final BooleanIntegerType INSTANCE = new BooleanIntegerType();

	public BooleanIntegerType() {
		this( SmallIntTypeDescriptor.INSTANCE, BooleanTypeDescriptor.INSTANCE );
	}
	protected BooleanIntegerType(SqlTypeDescriptor sqlTypeDescriptor, BooleanTypeDescriptor javaTypeDescriptor) {
		super( sqlTypeDescriptor, javaTypeDescriptor );
	}

	public String getName() {
		return "integer_boolean";
	}

}
/* hibernate pre 3.6 version
    @Override
    public Object get(ResultSet rs, String name) throws SQLException {
        Integer code = rs.getInt(name);
        if(code==null)
            return null;
        
        return code.intValue()==0 ? Boolean.FALSE : Boolean.TRUE;
    }

    @Override
    public String getName() {
        return "integer_boolean";
    }

    @Override
    public String objectToSQLString(Object value, Dialect dialect) throws Exception {
        return ((Boolean) value).booleanValue() ? "1" : "0";
    }

    @Override
    public void set(PreparedStatement st, Object value, int index) throws SQLException {
        st.setInt(index, toInt(value));
    }

    @Override
    public int sqlType() {
        return Types.SMALLINT;
    }

    @Override
    public Object stringToObject(String xml) throws Exception {
        if("1".equals(xml))
            return Boolean.TRUE;
        else if("0".equals(xml))
            return Boolean.FALSE;
        else
            throw new HibernateException("Could not interpret " + xml);
    }
    
    private int toInt(Object value) {
        return ((Boolean) value).booleanValue() ? 1 : 0;
    }
*/    
