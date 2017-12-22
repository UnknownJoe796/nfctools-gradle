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
package org.nfctools.mf.ul;

import org.nfctools.api.TagInfoReader;
import org.nfctools.mf.block.MfBlock;

import java.io.IOException;

public interface MfUlReaderWriter extends TagInfoReader {

    MfBlock[] readBlock(int startPage, int pagesToRead) throws IOException;

    void writeBlock(int startPage, MfBlock... mfBlock) throws IOException;
}