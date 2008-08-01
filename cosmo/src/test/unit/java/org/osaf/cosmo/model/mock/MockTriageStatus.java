/*
 * Copyright 2007 Open Source Applications Foundation
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
package org.osaf.cosmo.model.mock;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.osaf.cosmo.model.TriageStatus;

/**
 * Represents a compound triage status value.
 */
public class MockTriageStatus implements TriageStatus {
    
    private Integer code = null;
    
    private BigDecimal rank = null;
    
    private Boolean autoTriage = null;
    
    public MockTriageStatus() {
    }
   
    /* (non-Javadoc)
     * @see org.osaf.cosmo.model.copy.InterfaceTriageStatus#getCode()
     */
    public Integer getCode() {
        return code;
    }

    /* (non-Javadoc)
     * @see org.osaf.cosmo.model.copy.InterfaceTriageStatus#setCode(java.lang.Integer)
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /* (non-Javadoc)
     * @see org.osaf.cosmo.model.copy.InterfaceTriageStatus#getRank()
     */
    public BigDecimal getRank() {
        return rank;
    }

    /* (non-Javadoc)
     * @see org.osaf.cosmo.model.copy.InterfaceTriageStatus#setRank(java.math.BigDecimal)
     */
    public void setRank(BigDecimal rank) {
        this.rank = rank;
    }

    /* (non-Javadoc)
     * @see org.osaf.cosmo.model.copy.InterfaceTriageStatus#getAutoTriage()
     */
    public Boolean getAutoTriage() {
        return autoTriage;
    }

    /* (non-Javadoc)
     * @see org.osaf.cosmo.model.copy.InterfaceTriageStatus#setAutoTriage(java.lang.Boolean)
     */
    public void setAutoTriage(Boolean autoTriage) {
        this.autoTriage = autoTriage;
    }
        
    /* (non-Javadoc)
     * @see org.osaf.cosmo.model.copy.InterfaceTriageStatus#copy()
     */
    public TriageStatus copy() {
        TriageStatus copy = new MockTriageStatus();
        copy.setCode(code);
        copy.setRank(rank);
        copy.setAutoTriage(autoTriage);
        return copy;
    }

    public String toString() {
        return new ToStringBuilder(this).
            append("code", code).
            append("rank", rank).
            append("autoTriage", autoTriage).
            toString();
    }

    public boolean equals(Object obj) {
        if (! (obj instanceof MockTriageStatus))
            return false;
        if (this == obj)
            return true;
        MockTriageStatus ts = (MockTriageStatus) obj;
        return new EqualsBuilder().
            append(code, ts.code).
            append(rank, ts.rank).
            append(autoTriage, ts.autoTriage).
            isEquals();
    }

    public static TriageStatus createInitialized() {
        TriageStatus ts = new MockTriageStatus();
        ts.setCode(new Integer(CODE_NOW));
        // XXX there's gotta be a better way!
        String time = (System.currentTimeMillis() / 1000) + ".00";
        ts.setRank(new BigDecimal(time).negate());
        ts.setAutoTriage(Boolean.TRUE);
        return ts;
    }

    public static String label(Integer code) {
        if (code.equals(CODE_NOW))
            return LABEL_NOW;
        if (code.equals(CODE_LATER))
            return LABEL_LATER;
        if (code.equals(CODE_DONE))
            return LABEL_DONE;
        throw new IllegalStateException("Unknown code " + code);
    }

    public static Integer code(String label) {
        if (label.equals(LABEL_NOW))
            return new Integer(CODE_NOW);
        if (label.equals(LABEL_LATER))
            return new Integer(CODE_LATER);
        if (label.equals(LABEL_DONE))
            return new Integer(CODE_DONE);
        throw new IllegalStateException("Unknown label " + label);
    }
}
