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

package org.dromara.soul.plugin.apache.dubbo;

import org.dromara.soul.cache.api.PluginDataSubscriber;
import org.dromara.soul.common.config.DubboRegisterConfig;
import org.dromara.soul.common.dto.PluginData;
import org.dromara.soul.common.enums.PluginEnum;
import org.dromara.soul.common.utils.GsonUtils;
import org.dromara.soul.plugin.api.Singleton;

import java.util.Objects;

public class ApacheDubboPluginSubscriber implements PluginDataSubscriber {
    
    @Override
    public void onSubscribe(PluginData pluginData) {
        if (null != pluginData && pluginData.getEnabled() && PluginEnum.DUBBO.getName().equals(pluginData.getName())) {
            DubboRegisterConfig dubboRegisterConfig = GsonUtils.getInstance().fromJson(pluginData.getConfig(), DubboRegisterConfig.class);
            DubboRegisterConfig exist = Singleton.INST.get(DubboRegisterConfig.class);
            if (Objects.nonNull(dubboRegisterConfig)) {
                if (Objects.isNull(exist) || !dubboRegisterConfig.equals(exist)) {
                    //如果是空，进行初始化操作，
                    ApplicationConfigCache.getInstance().init(dubboRegisterConfig);
                    ApplicationConfigCache.getInstance().invalidateAll();
                }
                Singleton.INST.single(DubboRegisterConfig.class, dubboRegisterConfig);
            }
        }
        
    }
}