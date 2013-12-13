package org.apache.usergrid.persistence.collection;


import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.apache.usergrid.persistence.collection.guice.TestCollectionModule;
import org.apache.usergrid.persistence.collection.impl.CollectionScopeImpl;
import org.apache.usergrid.persistence.model.entity.SimpleId;

import com.google.inject.Inject;
import com.google.inject.ProvisionException;

import static org.junit.Assert.assertNotNull;


/**
 * Basic tests
 *
 * @author tnine
 */
@RunWith( JukitoRunner.class )
@UseModules( { TestCollectionModule.class } )
public class EntityCollectionManagerFactoryTest {
    @Inject
    private EntityCollectionManagerFactory entityCollectionManagerFactory;


    @Test
    public void validInput() {

        CollectionScopeImpl context = new CollectionScopeImpl(new SimpleId( "organization" ), new SimpleId( "test" ), "test" );

        EntityCollectionManager entityCollectionManager =
                entityCollectionManagerFactory.createCollectionManager( context );

        assertNotNull( "A collection manager must be returned", entityCollectionManager );
    }


    @Test(expected = ProvisionException.class)
    public void nullInput() {
        EntityCollectionManager entityCollectionManager =
                entityCollectionManagerFactory.createCollectionManager( null );
    }
}
