package database;

import database.exception.FailedDataBaseCreationException;
import database.exception.FailedDataBaseQueryException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class RepositoryImplementationTest {

    private RepositoryImplementation repositoryImplementation;

    @Before
    public void setup() throws FailedDataBaseCreationException {
        this.repositoryImplementation = new RepositoryImplementation();
    }

    @Test
    public void shouldReturnTrueWhenCalledExists() throws FailedDataBaseQueryException {
        repositoryImplementation.save(new UserInformation("test", 1));

        boolean exists = repositoryImplementation.exists("test");

        assertTrue(exists);
    }

    @Test
    public void shouldReturnUserInformationHealthWhenGetByName() throws FailedDataBaseQueryException {
        repositoryImplementation.save(new UserInformation("test", 1));

        UserInformation userInformation = repositoryImplementation.getByName("test");

        assertEquals(1, userInformation.getHealth());
    }

    @Test
    public void shouldReturnUserInformationNameWhenGetByName() throws FailedDataBaseQueryException {
        repositoryImplementation.save(new UserInformation("test", 1));

        UserInformation userInformation = repositoryImplementation.getByName("test");

        assertEquals("test", userInformation.getName());
    }

    @Test
    public void shouldUpdateUserInformationWhenObjectAlreadyExist() throws FailedDataBaseQueryException {
        repositoryImplementation.save(new UserInformation("test", 1));

        repositoryImplementation.save(new UserInformation("test", 2));

        UserInformation userInformation = repositoryImplementation.getByName("test");
        assertEquals(2, userInformation.getHealth());
    }

    @After
    public void cleanUp() {
        repositoryImplementation.clean();
    }
}
