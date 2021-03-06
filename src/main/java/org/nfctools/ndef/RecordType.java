/**
 * Copyright 2011-2012 Adrian Stabiszewski, as@nfctools.org
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nfctools.ndef;

import java.util.Arrays;

public class RecordType {

    private byte[] type;

    public RecordType(byte[] type) {
        this.type = type;
    }

    public RecordType(String type) {
        this.type = type.getBytes(NdefConstants.DEFAULT_CHARSET);
    }

    public byte[] getType() {
        return type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(type);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RecordType other = (RecordType) obj;
        if (!Arrays.equals(type, other.type))
            return false;
        return true;
    }
}
