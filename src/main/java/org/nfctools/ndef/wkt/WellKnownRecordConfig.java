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
package org.nfctools.ndef.wkt;

import org.nfctools.ndef.Record;
import org.nfctools.ndef.RecordType;

public class WellKnownRecordConfig {

    private RecordType recordType;
    private Class<? extends Record> recordClass;
    private WellKnownRecordPayloadEncoder payloadEncoder;
    private WellKnownRecordPayloadDecoder payloadDecoder;

    public WellKnownRecordConfig(RecordType recordType, Class<? extends Record> recordClass,
                                 WellKnownRecordPayloadEncoder payloadEncoder, WellKnownRecordPayloadDecoder payloadDecoder) {
        this.recordType = recordType;
        this.recordClass = recordClass;
        this.payloadEncoder = payloadEncoder;
        this.payloadDecoder = payloadDecoder;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public Class<? extends Record> getRecordClass() {
        return recordClass;
    }

    public WellKnownRecordPayloadEncoder getPayloadEncoder() {
        return payloadEncoder;
    }

    public WellKnownRecordPayloadDecoder getPayloadDecoder() {
        return payloadDecoder;
    }
}
