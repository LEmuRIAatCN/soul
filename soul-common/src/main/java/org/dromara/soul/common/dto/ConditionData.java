/*
 *
 *  * Licensed to the Apache Software Foundation (ASF) under one or more
 *  * contributor license agreements.  See the NOTICE file distributed with
 *  * this work for additional information regarding copyright ownership.
 *  * The ASF licenses this file to You under the Apache License, Version 2.0
 *  * (the "License"); you may not use this file except in compliance with
 *  * the License.  You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.dromara.soul.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ConditionDTO.
 *
 * @author xiaoyu(Myth)
 * @author huangxiaofeng
 * @since 2.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConditionData implements Serializable {

    private static final long serialVersionUID = -6768497298941824781L;

    /**
     * param type (post  query  uri).
     */
    private String paramType;

    /**
     * {@linkplain org.dromara.soul.common.enums.OperatorEnum}.
     */
    private String operator;

    /**
     * param name.
     */
    private String paramName;

    /**
     * param value.
     */
    private String paramValue;
}
