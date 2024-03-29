package com.example.cityswift.server.repository;


import com.example.cityswift.dto.AddressDTO;
import com.example.cityswift.dto.BasicUserData;
import com.example.cityswift.dto.CreateUserData;
import com.example.cityswift.server.mapper.ToMailModelMapper;
import com.example.cityswift.server.mapper.ToUserModelMapper;
import com.example.cityswift.server.mapper.UserLoginMapper;
import com.example.cityswift.server.model.AddressModel;
import com.example.cityswift.server.model.UserModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    GenericRepository<UserModel> repository = new GenericRepository<>();
    AddressRepository addressRepository = new AddressRepository();
    ToUserModelMapper mapper = new ToUserModelMapper();
    UserLoginMapper userLoginMapper = new UserLoginMapper();
    ToMailModelMapper toMailModelMapper = new ToMailModelMapper();

    public Optional<UserModel> fetchUserById(int userId) {
        String sql = "SELECT * FROM app_user WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(userId);
        return repository.fetchSingleRow(sql, mapper, params);
    }

    public BasicUserData fetchBasicUserDataById(int userId) {
        Optional<UserModel> userModel = fetchUserById(userId);
        List<AddressModel> addressModel = addressRepository.fetchAddressesByUserId(userId);
        return userModel.map(model -> new BasicUserData(model, addressModel)).orElseGet(() -> new BasicUserData(null, null));
    }

    public Optional<UserModel> fetchUserCredentials(String username, String password) {
        String sql = "SELECT * FROM app_user WHERE mail = ? and password=?  ";
        List<Object> params = new ArrayList<>();
        params.add(username);
        params.add(password);
        return repository.fetchSingleRow(sql, userLoginMapper, params);
    }

    public int insertUser(CreateUserData createUserData, int privateToken, int publicToken) {
        String sql = "INSERT INTO app_user (mail, password, first_name, last_name, mobile, date_of_birth, private_token, public_token) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(createUserData.getMail());
        params.add(createUserData.getPassword());
        params.add(createUserData.getFirstName());
        params.add(createUserData.getLastName());
        params.add(createUserData.getMobile());
        params.add(createUserData.getDateOfBirth());
        params.add(privateToken);
        params.add(publicToken);
        return repository.insert(sql, params);
    }

    public Optional<UserModel> getUserByMail(String mail) {
        String sql = "SELECT * FROM app_user WHERE mail = ? ";
        List<Object> params = new ArrayList<>();
        params.add(mail);
        return repository.fetchSingleRow(sql, mapper, params);
    }

    public List<UserModel> fetchUserSearch(String userSearch, Integer privateToken) {
        String sql = "SELECT * FROM app_user WHERE (first_name LIKE ? OR last_name LIKE ? OR mail LIKE ? OR mobile LIKE ? OR CAST(public_token AS TEXT) LIKE ?)" +
                "AND id != ?";
        List<Object> params = new ArrayList<>();
        params.add('%' + userSearch + '%');
        params.add('%' + userSearch + '%');
        params.add('%' + userSearch + '%');
        params.add('%' + userSearch + '%');
        params.add('%' + userSearch + '%');
        params.add(privateToken);
        return repository.fetchMultipleRow(sql, mapper, params);
    }

    public Optional<UserModel> getUserByPublicToken(int data) {
        String sql = "SELECT * FROM app_user WHERE public_token = ?";
        List<Object> params = new ArrayList<>();
        params.add(data);
        return repository.fetchSingleRow(sql, mapper, params);
    }

    public Optional<UserModel> getUserById(int id) {
        String sql = "SELECT * FROM app_user WHERE id = ? ";
        List<Object> params = new ArrayList<>();
        params.add(id);
        return repository.fetchSingleRow(sql, mapper, params);
    }

    public Optional<UserModel> getUserByPrivateToken(Integer data) {
        String sql = "SELECT * FROM app_user WHERE private_token = ? ";
        List<Object> params = new ArrayList<>();
        params.add(data);
        return repository.fetchSingleRow(sql, mapper, params);
    }

    public List<UserModel> getFriends(Boolean accepted, Integer privateToken) {
        String sql = "SELECT * FROM app_user WHERE id IN (SELECT app_user_id_1 FROM friendship WHERE app_user_id_2 = ? AND accepted = ?)";
        List<Object> params = new ArrayList<>();
        params.add(privateToken);
        params.add(accepted);
        return repository.fetchMultipleRow(sql, mapper, params);
    }

    public List<UserModel> getAcceptedFriends(Integer privateToken) {
        String sql = "SELECT * FROM app_user WHERE id IN (SELECT CASE WHEN app_user_id_2 = ? THEN app_user_id_1 WHEN app_user_id_1 = ? THEN app_user_id_2 END FROM friendship WHERE accepted = true);";
        List<Object> params = new ArrayList<>();
        params.add(privateToken);
        params.add(privateToken);
        return repository.fetchMultipleRow(sql, mapper, params);
    }

    public void updateUserMoney(int id, BigDecimal v) {
        String sql = "UPDATE app_user SET money = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(v);
        params.add(id);
        repository.update(sql, params);
    }

    public int fetchTotalNumberOfOrders(int userId) {
        String sql = "SELECT COUNT(*) FROM orders";
        List<Object> params = new ArrayList<>();
        return repository.fetchCount(sql, params);
    }

    public int fetchYourNumberOfOrders(int userId) {
        String sql = "SELECT COUNT(*) FROM orders WHERE sender_id = ?";
        List<Object> params = new ArrayList<>();
        params.add(userId);
        return repository.fetchCount(sql, params);
    }


    public Optional<UserModel> getUserMail(String mail) {
        String sql = "SELECT app_user.mail FROM app_user WHERE mail = ? ";
        List<Object> params = new ArrayList<>();
        params.add(mail);
        return repository.fetchSingleRow(sql, toMailModelMapper, params);
    }

    public void changePassword(String mail, String password) {
        String sql = "UPDATE app_user SET password = ? WHERE mail = ?";
        List<Object> params = new ArrayList<>();
        params.add(password);
        params.add(mail);

        repository.insert(sql, params);
    }

    public void changePassword(String password, Integer privateToken) {
        String sql = "UPDATE app_user SET password = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(password);
        params.add(privateToken);

        repository.insert(sql, params);
    }

    public void changeMobile(String mobile, Integer privateToken) {
        String sql = "UPDATE app_user SET mobile = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(mobile);
        params.add(privateToken);

        repository.insert(sql, params);
    }

    public void changeAddress(AddressDTO newAddress, Integer privateToken) {
        String sql = "UPDATE address SET city = ?, street = ?, postal_code = ?, home_number = ?, door_key = ?  WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(newAddress.getCity());
        params.add(newAddress.getStreetName());
        params.add(newAddress.getPostalCode());
        params.add(newAddress.getHomeNr());
        params.add(newAddress.getDoorKey());
        params.add(privateToken);

        repository.insert(sql, params);
    }
}
