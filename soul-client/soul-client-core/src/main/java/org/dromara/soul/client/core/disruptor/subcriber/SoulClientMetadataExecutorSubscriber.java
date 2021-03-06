/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dromara.soul.client.core.disruptor.subcriber;

import org.dromara.soul.client.core.shutdown.SoulClientShutdownHook;
import org.dromara.soul.register.client.api.SoulClientRegisterRepository;
import org.dromara.soul.register.common.dto.MetaDataRegisterDTO;
import org.dromara.soul.register.common.subsriber.ExecutorTypeSubscriber;
import org.dromara.soul.register.common.type.DataType;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * The type Metadata executor subscriber.
 *
 * @author xiaoyu
 */
public class SoulClientMetadataExecutorSubscriber implements ExecutorTypeSubscriber<MetaDataRegisterDTO> {
    
    private final SoulClientRegisterRepository soulClientRegisterRepository;
    
    /**
     * Instantiates a new Soul client metadata executor subscriber.
     *
     * @param soulClientRegisterRepository the soul client register repository
     */
    public SoulClientMetadataExecutorSubscriber(final SoulClientRegisterRepository soulClientRegisterRepository) {
        this.soulClientRegisterRepository = soulClientRegisterRepository;
    }
    
    @Override
    public DataType getType() {
        return DataType.META_DATA;
    }
    
    @Override
    public void executor(final Collection<MetaDataRegisterDTO> metaDataRegisterDTOList) {
        for (MetaDataRegisterDTO metaDataRegisterDTO : metaDataRegisterDTOList) {
            while (true) {
                try (Socket socket = new Socket(metaDataRegisterDTO.getHost(), metaDataRegisterDTO.getPort())) {
                    break;
                } catch (IOException e) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            SoulClientShutdownHook.delayOtherHooks();
            soulClientRegisterRepository.persistInterface(metaDataRegisterDTO);
        }
    }
}
