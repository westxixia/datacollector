/*
 * Copyright 2017 StreamSets Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.pipeline.stage.origin.mysql;

import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.ConfigGroups;
import com.streamsets.pipeline.api.ExecutionMode;
import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.StageDef;

@StageDef(
    version = 2,
    label = "MySQL Binary Log",
    description = "Reads MySQL binary log from MySQL server.",
    icon = "mysql.png",
    execution = ExecutionMode.STANDALONE,
    resetOffset = true,
    recordsByRef = true,
    upgrader = MySqlSourceUpgrader.class,
    upgraderDef = "upgrader/MysqlDSource.yaml",
    onlineHelpRefUrl = "index.html?contextID=task_qbt_kyh_xx"
)
@ConfigGroups(value = Groups.class)
@GenerateResourceBundle
public class MysqlDSource extends MysqlSource {
  @ConfigDefBean
  public MysqlSourceConfig config;

  @Override
  public MysqlSourceConfig getConfig() {
    return config;
  }
}
