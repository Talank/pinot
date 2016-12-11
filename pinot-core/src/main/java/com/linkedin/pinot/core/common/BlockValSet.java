/**
 * Copyright (C) 2014-2016 LinkedIn Corp. (pinot-core@linkedin.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.linkedin.pinot.core.common;

import com.linkedin.pinot.common.data.FieldSpec.DataType;

/**
 *
 *
 */
public interface BlockValSet {

  BlockValIterator iterator();

  DataType getValueType();

  /**
   * Get Integer values for the given docIds.
   *
   * @param inDocIds Input docIds
   * @param inStartPos Start index in inDocIds
   * @param inDocIdsSize Number of input doc ids
   * @param outValues Output array
   * @param outStartPos Start position in outValues
   */
  void getIntValues(int[] inDocIds, int inStartPos, int inDocIdsSize, int[] outValues, int outStartPos);

  /**
   * Get long values for the given docIds.
   *
   * @param inDocIds Input docIds
   * @param inStartPos Start index in inDocIds
   * @param inDocIdsSize Number of input doc ids
   * @param outValues Output array
   * @param outStartPos Start position in outValues
   */
  void getLongValues(int[] inDocIds, int inStartPos, int inDocIdsSize, long[] outValues, int outStartPos);

  /**
   * Get float values for the given docIds.
   *
   * @param inDocIds Input docIds
   * @param inStartPos Start index in inDocIds
   * @param inDocIdsSize Number of input doc ids
   * @param outValues Output array
   * @param outStartPos Start position in outValues
   */
  void getFloatValues(int[] inDocIds, int inStartPos, int inDocIdsSize, float[] outValues, int outStartPos);

  /**
   *
   * @param inDocIds Input docIds
   * @param inStartPos Start index in inDocIds
   * @param inDocIdsSize Number of input doc ids
   * @param outValues Output array
   * @param outStartPos Start position in outValues
   */
  void getDoubleValues(int[] inDocIds, int inStartPos, int inDocIdsSize, double[] outValues, int outStartPos);

  /**
   * Get string values for the given docIds.
   *
   * @param inDocIds Input docIds
   * @param inStartPos Start index in inDocIds
   * @param inDocIdsSize Number of input doc ids
   * @param outValues Output array
   * @param outStartPos Start position in outValues
   */
  void getStringValues(int[] inDocIds, int inStartPos, int inDocIdsSize, String[] outValues, int outStartPos);

  /**
   * Get values for single-valued column.
   *
   * @param <T> Return type
   * @return Values for single-valued column.
   */
  <T> T getSingleValues();

  /**
   * Get values for multi-valued column.
   *
   * @param <T> Return type
   * @return Values for multi-valued column.
   *
   * TODO: Re-visit batch reading of multi-valued columns.
   */
  <T> T getMultiValues();

  /**
   * Get the dictionary ids for all docs of this block.
   * This version is for single-valued columns.
   */
  int[] getDictionaryIds();

  /**
   * Copies the dictionaryIds for the input range DocIds.
   * Expects that the out array is properly sized
   * @param inDocIds input set of doc ids for which to read dictionaryIds
   * @param inStartPos start index in inDocIds
   * @param inDocIdsSize size of inDocIds
   * @param outDictionaryIds out parameter giving the dictionary ids corresponding to
   *                         input docIds
   * @param outStartPos starting index position in outDictionaryIds. Indexes will
   *                    be copied starting at this position.
   *                    outDictionaryIds must be atleast (outStartPos + inDocIdsSize) in size
   * TODO: Remove arguments from this api, as ProjectionBlock has all the required info.
   */
  void getDictionaryIds(int[] inDocIds, int inStartPos, int inDocIdsSize, int[] outDictionaryIds, int outStartPos);

  /**
   * Fills dictionary id's of multi-valued column for the current doc id in the passed in array,
   * and returns the total number of multi-values read.
   * Caller responsible to ensure that the passed in array is large enough to store the result.
   *
   * @param docId Doc id for which to get the dictionary ids.
   * @param outputDictIds int array where the resulting dictionary ids will be stored.
   * @return Total number of multi-valued columns.
   */
  int getDictionaryIdsForDocId(int docId, int[] outputDictIds);
}
