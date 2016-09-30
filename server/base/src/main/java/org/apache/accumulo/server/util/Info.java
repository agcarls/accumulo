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
package org.apache.accumulo.server.util;

import org.apache.accumulo.core.client.Instance;
import org.apache.accumulo.core.util.MonitorUtil;
import org.apache.accumulo.server.client.HdfsZooInstance;
import org.apache.accumulo.start.spi.KeywordExecutable;
import org.apache.zookeeper.KeeperException;

import com.google.auto.service.AutoService;

@AutoService(KeywordExecutable.class)
public class Info implements KeywordExecutable {

  @Override
  public String keyword() {
    return "info";
  }

  @Override
  public String description() {
    return "Print Accumulo cluster info";
  }

  @Override
  public void execute(final String[] args) throws KeeperException, InterruptedException {
    Instance instance = HdfsZooInstance.getInstance();
    System.out.println("monitor: " + MonitorUtil.getLocation(instance));
    System.out.println("masters: " + instance.getMasterLocations());
    System.out.println("zookeepers: " + instance.getZooKeepers());
  }

  public static void main(String[] args) throws Exception {
    new Info().execute(args);
  }
}
