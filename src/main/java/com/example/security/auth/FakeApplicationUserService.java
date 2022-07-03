package com.example.security.auth;

import com.example.security.security.ApplicationUserRole;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.security.security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserService implements ApplicationUserDAO{
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> applicationUser.getUsername().equals(username))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(){
        return Lists.newArrayList(
                new ApplicationUser(
                        "student",
                        passwordEncoder.encode("student"),
                        STUDENT.geGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser(
                        "admin",
                        passwordEncoder.encode("admin"),
                        ADMIN.geGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser(
                        "tom",
                        passwordEncoder.encode("tom"),
                        ADMINTRAINEE.geGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true)
        );
    }
}
