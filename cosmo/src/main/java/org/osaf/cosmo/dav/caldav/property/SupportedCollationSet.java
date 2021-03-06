/*
 * Copyright 2005-2007 Open Source Applications Foundation
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
package org.osaf.cosmo.dav.caldav.property;

import java.util.HashSet;
import java.util.Set;

import org.apache.jackrabbit.webdav.xml.DomUtil;
import org.osaf.cosmo.calendar.util.CalendarUtils;
import org.osaf.cosmo.dav.caldav.CaldavConstants;
import org.osaf.cosmo.dav.property.StandardDavProperty;
import org.osaf.cosmo.icalendar.ICalendarConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Represents the CalDAV supported-collation-set
 * property.
 */
public class SupportedCollationSet extends StandardDavProperty
    implements CaldavConstants, ICalendarConstants {

    public SupportedCollationSet() {
        this(SUPPORTED_COLLATIONS);
    }

    public SupportedCollationSet(Set<String> collations) {
        this((String[]) collations.toArray(new String[0]));
    }

    public SupportedCollationSet(String[] collations) {
        super(SUPPORTEDCOLLATIONSET, collations(collations), true);
        for (String collation :collations) {
            if (! CalendarUtils.isSupportedCollation(collation)) {
                throw new IllegalArgumentException("Invalid collation '" +
                                                   collation + "'.");
            }
        }
    }

    private static HashSet<String> collations(String[] collations) {
        HashSet<String> collationSet = new HashSet<String>();
        
        for (String c : collations)
            collationSet.add(c);
        return collationSet;
    }

    public Set<String> getCollations() {
        return (Set<String>) getValue();
    }
    
    public Element toXml(Document document) {
        Element name = getName().toXml(document);

        for (String collation : getCollations()) {
            Element e = DomUtil.createElement(document,
                    ELEMENT_CALDAV_SUPPORTEDCOLLATION, NAMESPACE_CALDAV);
            DomUtil.setText(e, collation);
            name.appendChild(e);
        }

        return name;
    }
}
