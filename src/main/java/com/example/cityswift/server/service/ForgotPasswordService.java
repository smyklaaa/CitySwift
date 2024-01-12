package com.example.cityswift.server.service;

import com.example.cityswift.dto.ServerResponse;
import com.example.cityswift.server.model.UserModel;
import com.example.cityswift.server.repository.ForgotPasswordRepository;
import com.example.cityswift.server.repository.SettingsRepository;
import com.example.cityswift.server.util.MailSender;

import java.security.SecureRandom;
import java.util.Optional;

public class ForgotPasswordService {
    private final ForgotPasswordRepository forgotPasswordRepository = new ForgotPasswordRepository();

    public ServerResponse changeForgotPasswordIfUserRegistered(UserModel data) {
        Optional<UserModel> userInDatabase = forgotPasswordRepository.getUserMail(data.getMail());

        if(userInDatabase.isPresent()){
            String newPassword  = generateRandomPassword();
            forgotPasswordRepository.changePassword(userInDatabase.get().getMail(),newPassword);

            MailSender mailSender = new MailSender();
            mailSender.sendMail(userInDatabase.get().getMail(),"Nowe hasło do platformy CitySwift",
                    "Oto nowe hasło do platformy CitySwift: "+newPassword
                            +"  możesz je zmienić w aplikacji po zalogowaniu");
            ServerResponse serverResponse = new ServerResponse();
            serverResponse.setResultCode(200);
            return serverResponse;
        }else{
            ServerResponse serverResponse = new ServerResponse();
            serverResponse.setResultCode(404);
            return serverResponse;
        }
    }


    public static String generateRandomPassword() {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=<>?";
        int passwordLength = 12;
        SecureRandom random = new SecureRandom();
        StringBuilder passwordBuilder = new StringBuilder();

        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            passwordBuilder.append(allowedChars.charAt(randomIndex));
        }
        return passwordBuilder.toString();
    }

}
