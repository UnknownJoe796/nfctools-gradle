/**
 * Copyright 2011 Adrian Stabiszewski, as@nfctools.org
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

package org.nfctools.ndef.auri;

import org.nfctools.ndef.*;
import org.nfctools.ndef.wkt.encoder.RecordEncoder;

/**
 * @author Thomas Rorvik Skjolberg (skjolber@gmail.com)
 */

public class AbsoluteUriRecordEncoder implements RecordEncoder {

    @Override
    public boolean canEncode(Record record) {
        return record instanceof AbsoluteUriRecord;
    }

    @Override
    public NdefRecord encodeRecord(Record record, NdefMessageEncoder messageEncoder) {
        AbsoluteUriRecord absoluteUriRecord = (AbsoluteUriRecord) record;

        if (!absoluteUriRecord.hasUri()) {
            throw new NdefEncoderException("Expected URI", record);
        }

        return new NdefRecord(NdefConstants.TNF_ABSOLUTE_URI, AbsoluteUriRecord.TYPE, absoluteUriRecord.getId(),
                absoluteUriRecord.getUri().getBytes(NdefConstants.DEFAULT_CHARSET));
    }
}
