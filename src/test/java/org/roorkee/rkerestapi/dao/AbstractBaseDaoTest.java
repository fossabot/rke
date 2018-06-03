package org.roorkee.rkerestapi.dao;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.Test;
import org.roorkee.rkerestapi.entity.AbstractEntity;
import org.roorkee.rkerestapi.entity.Content;
import org.roorkee.rkerestapi.util.RkeException;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractBaseDaoTest<T extends AbstractEntity> {

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    protected void setUp() throws Exception{
        helper.setUp();
    }

    protected void tearDown() throws Exception {
        helper.tearDown();
    }

    protected void create_positive() {
        T obj = createMockObj(1L);
        long out = getDao().save(obj);
        assertThat(out).isPositive();
    }

    protected void get_positive(){
        T obj = createMockObj(1L);
        long out = getDao().save(obj);
        obj.setId(out);
        assertThat(out).isNotNull();
        AbstractEntity dbContent = getDao().get(out);
        assertThat(dbContent).isEqualToComparingFieldByField(obj);
    }

    protected void delete_positive(){
        T obj = createMockObj(1L);
        long out = getDao().save(obj);
        assertThat(out).isNotNull();
        getDao().delete(out);
        try {
            AbstractEntity dbContent = getDao().get(out);
        }
        catch (RkeException e){
            assertThat(e).isNotNull();
        }
    }

    protected abstract T createMockObj(long id);

    protected abstract AbstractDao getDao();
}
