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

import org.nfctools.ndef.*;
import org.nfctools.ndef.wkt.encoder.RecordEncoder;
import org.nfctools.ndef.wkt.records.WellKnownRecord;

import java.util.HashMap;
import java.util.Map;

public class WellKnownRecordEncoder implements RecordEncoder {

    private Map<Class<?>, WellKnownRecordConfig> knownRecordTypes = new HashMap<Class<?>, WellKnownRecordConfig>();

    @Override
    public boolean canEncode(Record record) {
        return knownRecordTypes.containsKey(record.getClass());
    }

    @Override
    public NdefRecord encodeRecord(Record record, NdefMessageEncoder messageEncoder) {

        byte[] key = record.getId();
        if (key != null) {
            if (key.length > 255) {
                throw new NdefEncoderException("Expected record id length <= 255 bytes", record);
            }
        }

        WellKnownRecordConfig config = knownRecordTypes.get(record.getClass());
        byte[] payload = config.getPayloadEncoder().encodePayload((WellKnownRecord) record, messageEncoder);
        byte[] type = config.getRecordType().getType();
        return new NdefRecord(NdefConstants.TNF_WELL_KNOWN, type, key, payload);
    }

    public void addRecordConfig(WellKnownRecordConfig config) {
        knownRecordTypes.put(config.getRecordClass(), config);
    }
}
