/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.usergrid.persistence.collection.serialization.impl;


import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.apache.usergrid.persistence.collection.MvccEntity;
import org.apache.usergrid.persistence.collection.guice.TestCollectionModule;
import org.apache.usergrid.persistence.collection.mvcc.MvccEntitySerializationStrategy;
import org.apache.usergrid.persistence.collection.util.EntityHelper;
import org.apache.usergrid.persistence.core.guice.ProxyImpl;
import org.apache.usergrid.persistence.core.migration.data.MigrationInfoSerialization;
import org.apache.usergrid.persistence.core.test.ITRunner;
import org.apache.usergrid.persistence.core.test.UseModules;

import com.google.inject.Inject;

import static org.junit.Assert.assertTrue;


@RunWith( ITRunner.class )
@UseModules( TestCollectionModule.class )
public class MvccEntitySerializationStrategyProxyV2Test extends MvccEntitySerializationStrategyV2Test {

    @Inject
    @ProxyImpl
    private MvccEntitySerializationStrategy serializationStrategy;


    @Override
    protected MvccEntitySerializationStrategy getMvccEntitySerializationStrategy() {
        return serializationStrategy;
    }


    @Inject
    protected MigrationInfoSerialization migrationInfoSerialization;

    private int existingVersion;


    /**
     * We need to run our migration to ensure that we are on the current version, and everything still functions
     * correctly
     */
    @Before
    public void setMigrationVersion() {
        existingVersion = migrationInfoSerialization.getVersion();

        //set our new version, so that is will run through the new code
        migrationInfoSerialization.setVersion( MvccEntitySerializationStrategyProxyImpl.MIGRATION_VERSION );
    }




    @After
    public void reSetMigrationVersion() {
        migrationInfoSerialization.setVersion( existingVersion );
    }
}