package tests;

import com.bsuir.by.library.bean.User;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.user.implementation.UserDao;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by User on 24.04.2017.
 */
public class AbstractDaoControllerTest {
    @Test(expected = NullPointerException.class)
    public void testUserDaoGetTable() throws SQLException, DaoException {
        UserDao userDao = new UserDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        userDao.startConnectionToDB(resourceBundle.getString("url"), "eqwe", resourceBundle.getString("password"));
//        ExpectedException exception = ExpectedException.none();
//        exception.expect(NullPointerException.class);
        List<User> userList= userDao.getDataFromDB("SELECT * FROM lib_user WHERE lib_user_id=1","");
    }

}