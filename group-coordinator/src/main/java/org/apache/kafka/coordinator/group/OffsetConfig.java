/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.kafka.coordinator.group;

import org.apache.kafka.common.record.CompressionType;

public class OffsetConfig {
    public final int maxMetadataSize;
    public final int loadBufferSize;
    public final long offsetsRetentionMs;
    public final long offsetsRetentionCheckIntervalMs;
    public final int offsetsTopicNumPartitions;
    public final int offsetsTopicSegmentBytes;
    public final short offsetsTopicReplicationFactor;
    public final CompressionType offsetsTopicCompressionType;
    public final int offsetCommitTimeoutMs;

    /**
     * Configuration settings for in-built offset management
     *
     * @param maxMetadataSize                 The maximum allowed metadata for any offset commit.
     * @param loadBufferSize                  Batch size for reading from the offsets segments when loading offsets into the cache.
     * @param offsetsRetentionMs              For subscribed consumers, committed offset of a specific partition will be expired and discarded when
     *                                        1) this retention period has elapsed after the consumer group loses all its consumers (i.e. becomes empty);
     *                                        2) this retention period has elapsed since the last time an offset is committed for the partition AND the group is no longer subscribed to the corresponding topic.
     *                                        For standalone consumers (using manual assignment), offsets will be expired after this retention period has elapsed since the time of last commit.
     *                                        Note that when a group is deleted via the delete-group request, its committed offsets will also be deleted immediately;
     *                                        Also when a topic is deleted via the delete-topic request, upon propagated metadata update any group's committed offsets for that topic will also be deleted without extra retention period
     * @param offsetsRetentionCheckIntervalMs Frequency at which to check for expired offsets.
     * @param offsetsTopicNumPartitions       The number of partitions for the offset commit topic (should not change after deployment).
     * @param offsetsTopicSegmentBytes        The offsets topic segment bytes should be kept relatively small to facilitate faster
     *                                        log compaction and faster offset loads
     * @param offsetsTopicReplicationFactor   The replication factor for the offset commit topic (set higher to ensure availability).
     * @param offsetsTopicCompressionType     Compression type for the offsets topic - compression should be turned on in
     *                                        order to achieve "atomic" commits.
     * @param offsetCommitTimeoutMs           The offset commit will be delayed until all replicas for the offsets topic receive the
     *                                        commit or this timeout is reached. (Similar to the producer request timeout.)
     */
    public OffsetConfig(int maxMetadataSize,
                        int loadBufferSize,
                        long offsetsRetentionMs,
                        long offsetsRetentionCheckIntervalMs,
                        int offsetsTopicNumPartitions,
                        int offsetsTopicSegmentBytes,
                        short offsetsTopicReplicationFactor,
                        CompressionType offsetsTopicCompressionType,
                        int offsetCommitTimeoutMs
    ) {
        this.maxMetadataSize = maxMetadataSize;
        this.loadBufferSize = loadBufferSize;
        this.offsetsRetentionMs = offsetsRetentionMs;
        this.offsetsRetentionCheckIntervalMs = offsetsRetentionCheckIntervalMs;
        this.offsetsTopicNumPartitions = offsetsTopicNumPartitions;
        this.offsetsTopicSegmentBytes = offsetsTopicSegmentBytes;
        this.offsetsTopicReplicationFactor = offsetsTopicReplicationFactor;
        this.offsetsTopicCompressionType = offsetsTopicCompressionType;
        this.offsetCommitTimeoutMs = offsetCommitTimeoutMs;
    }
}
